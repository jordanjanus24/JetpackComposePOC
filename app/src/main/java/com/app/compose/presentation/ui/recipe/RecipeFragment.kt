package com.app.compose.presentation.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.app.compose.presentation.BaseApplication
import com.app.compose.presentation.components.DefaultSnackbar
import com.app.compose.presentation.components.IMAGE_HEIGHT
import com.app.compose.presentation.components.RecipeView
import com.app.compose.presentation.components.shimmer.ShimmerLoading
import com.app.compose.presentation.components.util.SnackbarController
import com.app.compose.presentation.theme.AppTheme
import com.app.compose.presentation.ui.recipe.RecipeEvent.GetRecipeEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@AndroidEntryPoint
class RecipeFragment: Fragment() {

    @Inject
    lateinit var application: BaseApplication
    private val snackbarController = SnackbarController(lifecycleScope)
    private val viewModel: RecipeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt("recipeId")?.let { recipeId ->
            viewModel.onTriggerEvent(GetRecipeEvent(recipeId))
        }
    }
    @ExperimentalMaterialApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val loading = viewModel.loading.value
                val recipe = viewModel.recipe.value
                val scaffoldState = rememberScaffoldState()
                AppTheme(
                    darkTheme = application.isDark.value,
                    scaffoldState = scaffoldState
                ) {
                    Scaffold(
                        scaffoldState = scaffoldState,
                        snackbarHost = {
                            scaffoldState.snackbarHostState
                        }
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            if(loading && recipe == null) {
                                ShimmerLoading(imageHeight = IMAGE_HEIGHT.dp,shimmerCount = 1, underlines = 3)
                            } else {
                                recipe?.let {
                                    if(it.id == 1) {
                                        snackbarController.showSnackbar(
                                            scaffoldState = scaffoldState,
                                            message = "An error occured with this recipe.",
                                            actionLabel = "Ok"
                                        )
                                    } else {
                                        RecipeView(recipe = it)
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}