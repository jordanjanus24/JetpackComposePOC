package com.app.compose.presentation.ui.recipe_list

sealed class RecipeListEvent {
    object NewSearchEvent: RecipeListEvent()
    object NextPageEvent: RecipeListEvent()

    object RestoreStateEvent: RecipeListEvent()
}