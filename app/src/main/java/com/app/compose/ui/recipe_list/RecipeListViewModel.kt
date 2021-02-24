package com.app.compose.ui.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.compose.domain.model.Recipe
import com.app.compose.repository.RecipeRepository
import kotlinx.coroutines.launch

class RecipeListViewModel
@ViewModelInject
constructor(
    private val repository: RecipeRepository
): ViewModel() {
    val recipes: MutableState<List<Recipe>> = mutableStateOf(ArrayList())
    fun newSearch(){
        viewModelScope.launch {
            val result = repository.search(
                page = 1,
                query = "chicken"
            )
            recipes.value = result
        }
    }

}