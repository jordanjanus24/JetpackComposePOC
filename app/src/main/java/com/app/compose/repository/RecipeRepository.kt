package com.app.compose.repository

import com.app.compose.domain.model.Recipe

interface RecipeRepository {
    suspend fun search(page: Int, query: String): List<Recipe>
    suspend fun get(id:Int): Recipe
}