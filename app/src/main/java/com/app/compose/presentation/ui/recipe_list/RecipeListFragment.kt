package com.app.compose.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.app.compose.presentation.BaseApplication
import com.app.compose.presentation.components.RecipeList
import com.app.compose.presentation.components.SearchToolbar
import com.app.compose.presentation.components.util.SnackbarController
import com.app.compose.presentation.theme.AppTheme
import com.app.compose.presentation.ui.recipe_list.RecipeListEvent.NewSearchEvent
import com.app.compose.presentation.ui.recipe_list.RecipeListEvent.NextPageEvent
import com.app.compose.presentation.util.ConnectionLiveData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RecipeListFragment: Fragment() {

    @Inject
    lateinit var application: BaseApplication
    private val viewModel: RecipeListViewModel by activityViewModels()
    private val snackbarController = SnackbarController(lifecycleScope)

    @Inject
    lateinit var connectionLiveData: ConnectionLiveData

    @ExperimentalComposeUiApi
    @ExperimentalAnimationApi
    @ExperimentalMaterialApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       return ComposeView(requireContext()).apply {
            setContent {
                val recipes = viewModel.recipes.value
                val query = viewModel.query.value
                val selectedCategory = viewModel.selectedCategory.value
                val loading = viewModel.loading.value
                val page = viewModel.page.value
                val scaffoldState = rememberScaffoldState()
                AppTheme(
                    darkTheme = application.isDark.value,
                    scaffoldState = scaffoldState
                ) {
                    Scaffold(
                        scaffoldState = scaffoldState,
                        topBar = {
                            SearchToolbar(
                                query = query,
                                onQueryChanged = { viewModel.onQueryChanged(it) },
                                selectedCategory = selectedCategory,
                                categories = getAllFoodCategories(),
                                onSelectedCategoryChanged = { category ->
                                    viewModel.onSelectedCategoryChanged(category)
                                },
                                onExecuteSearch = {
                                    if(viewModel.selectedCategory.value?.value == "Milk") {
                                        snackbarController.getScope().launch {
                                            snackbarController.showSnackbar(
                                                scaffoldState = scaffoldState,
                                                message = "Invalid category: MILK",
                                                actionLabel = "Hide"
                                            )
                                        }
                                    } else {
                                        viewModel.onTriggerEvent(NewSearchEvent)
                                    }
                                },
                                onToggleTheme = { application.toggleLightTheme() },
                                connectionLiveData = connectionLiveData
                            )
                        },
                        snackbarHost = {
                            scaffoldState.snackbarHostState
                        }
                    ) {
                        RecipeList(
                            loading = loading,
                            recipes = recipes,
                            onChangeScrollPosition = viewModel::onChangeRecipeScrollPosition,
                            page = page,
                            onTriggerNextPage = {
                                viewModel.onTriggerEvent(NextPageEvent)
                            },
                            scaffoldState = scaffoldState,
                            snackbarController = snackbarController,
                            navController = findNavController()
                        )
                    }
                }
            }
       }
    }
}