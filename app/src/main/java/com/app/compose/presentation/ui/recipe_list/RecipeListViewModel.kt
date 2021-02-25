package com.app.compose.presentation.ui.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.compose.domain.model.Recipe
import com.app.compose.repository.RecipeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RecipeListViewModel
@ViewModelInject
constructor(
    private val repository: RecipeRepository
): ViewModel() {
    val recipes: MutableState<List<Recipe>> = mutableStateOf(ArrayList())
    val query = mutableStateOf("")
    val selectedCategory: MutableState<FoodCategory?> = mutableStateOf(null)
    var categoryScrollPosition: Float = 0f
    val loading = mutableStateOf(false)
    init {
        newSearch()
    }
    fun newSearch(){
        viewModelScope.launch {
            loading.value = true
            resetSearchState()
            delay(1000)
            val result = repository.search(
                page = 1,
                query = query.value
            )
            recipes.value = result
            loading.value = false
        }
    }
    private fun resetSearchState() {
        recipes.value = listOf()
        if(selectedCategory.value?.value != query.value) {
            selectedCategory.value = null
        }
    }

    fun onQueryChanged(query: String){
        this.query.value = query
    }

    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getFoodCategory(category)
        selectedCategory.value = newCategory
        onQueryChanged(category)
    }

    fun onChangeCategoryScrollPosition(position: Float){
        categoryScrollPosition = position
    }
}