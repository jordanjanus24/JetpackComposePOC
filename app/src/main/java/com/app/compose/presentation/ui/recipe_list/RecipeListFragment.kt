package com.app.compose.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.app.compose.presentation.BaseApplication
import com.app.compose.presentation.components.DefaultSnackbar
import com.app.compose.presentation.components.RecipeCard
import com.app.compose.presentation.components.RecipeList
import com.app.compose.presentation.components.SearchToolbar
import com.app.compose.presentation.components.shimmer.ShimmerLoading
import com.app.compose.presentation.components.util.SnackbarController
import com.app.compose.presentation.theme.AppTheme
import com.app.compose.presentation.ui.recipe_list.RecipeListEvent.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RecipeListFragment: Fragment() {

    @Inject
    lateinit var application: BaseApplication
    private val viewModel: RecipeListViewModel by activityViewModels()
    private val snackbarController = SnackbarController(lifecycleScope)

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
                val categoryScrollPosition = viewModel.categoryScrollPosition
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
                                categoryScrollPosition = categoryScrollPosition,
                                onSelectedCategoryChanged = { category, scrollPosition ->
                                    viewModel.onSelectedCategoryChanged(category)
                                    viewModel.onChangeCategoryScrollPosition(scrollPosition)
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
                                onToggleTheme = { application.toggleLightTheme() }
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