package com.app.compose.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.app.compose.presentation.BaseApplication
import com.app.compose.presentation.components.RecipeCard
import com.app.compose.presentation.components.SearchToolbar
import com.app.compose.presentation.components.shimmer.ShimmerLoading
import com.app.compose.presentation.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecipeListFragment: Fragment() {

    @Inject
    lateinit var application: BaseApplication
    private val viewModel: RecipeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       return ComposeView(requireContext()).apply {
            setContent {
                AppTheme(
                    darkTheme = application.isDark.value
                ) {
                    val recipes = viewModel.recipes.value
                    val query = viewModel.query.value
                    val selectedCategory = viewModel.selectedCategory.value
                    val categoryScrollPosition = viewModel.categoryScrollPosition
                    val loading = viewModel.loading.value
                    Scaffold(
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
                                onExecuteSearch = { viewModel.newSearch() },
                                onToggleTheme = { application.toggleLightTheme() }
                            )
                        }
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize()
                                .background(color = MaterialTheme.colors.background)
                        ) {
                            if(loading) {
                                ShimmerLoading(250.dp)
                            }
                            else {
                                LazyColumn {
                                    itemsIndexed(
                                        items = recipes
                                    ) { index, recipe ->
                                        RecipeCard(recipe = recipe, onClick = { })
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

@Composable
fun BottomBar() {
    BottomNavigation(elevation = 12.dp) {
        BottomNavigationItem(
            icon = { Icon(Icons.Default.BrokenImage)},
            selected = false,
            onClick = {}
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Search)},
            selected = true,
            onClick = {}
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.AccountBalanceWallet)},
            selected = false,
            onClick = {}
        )
    }
}

@Composable
fun Drawer() {
    Column {
        Text("Item1")
        Text("Item2")
        Text("Item3")
        Text("Item4")
        Text("Item5")
    }
}