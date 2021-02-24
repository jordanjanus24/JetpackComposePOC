package com.app.compose.repository

import com.app.compose.domain.model.Recipe
import com.app.compose.network.RecipeService
import com.app.compose.network.model.RecipeDTOMapper

class RecipeRepository_Impl (
    private val recipeService: RecipeService,
    private val mapper: RecipeDTOMapper
): RecipeRepository {
    private val token = "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
    override suspend fun search(page: Int, query: String): List<Recipe> {
        return mapper.toDomainList(recipeService.search(token = token, page = page, query = query).recipes)
    }

    override suspend fun get(id: Int): Recipe {
        return mapper.mapToDomainModel(recipeService.get(token = token, id))
    }

}