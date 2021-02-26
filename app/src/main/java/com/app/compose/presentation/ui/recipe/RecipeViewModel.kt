package com.app.compose.presentation.ui.recipe

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.compose.domain.model.Recipe
import com.app.compose.presentation.ui.recipe.RecipeEvent.*
import com.app.compose.repository.RecipeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val STATE_KEY_RECIPE = "state.key.recipeId"
class RecipeViewModel
@ViewModelInject
constructor(
    private val recipeRepository: RecipeRepository,
    @Assisted private val state: SavedStateHandle
): ViewModel() {
    val recipe: MutableState<Recipe?> = mutableStateOf(null)
    val loading = mutableStateOf(false)

    init {
        state.get<Int>(STATE_KEY_RECIPE)?.let { recipeId ->
            onTriggerEvent(GetRecipeEvent(recipeId))
        }
    }

    fun onTriggerEvent(event: RecipeEvent) {
        viewModelScope.launch {
            try {
                when (event) {
                    is GetRecipeEvent -> {
                        if (recipe.value == null) {
                            getRecipe(event.id)
                        }
                    }
                }
            } catch (e: Exception) {
            }

        }
    }

    private suspend fun getRecipe(id: Int) {
        loading.value = true
        delay(1000)
        val recipe = recipeRepository.get(id)
        this.recipe.value = recipe
        state.set(STATE_KEY_RECIPE, recipe.id)
        loading.value = false
    }
}