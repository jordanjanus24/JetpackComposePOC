package com.app.compose.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.app.compose.presentation.components.CircularIndeterminateProgressBar
import com.app.compose.presentation.components.RecipeCard
import com.app.compose.presentation.components.SearchToolbar
import com.app.compose.presentation.components.shimmer.ShimmerLoading
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeListFragment: Fragment() {

    private val viewModel: RecipeListViewModel by activityViewModels()

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
                Column {
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
                    )
                    Box(
                        modifier = Modifier.fillMaxSize()
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