package com.app.compose.repository

import com.app.compose.domain.model.Recipe
import com.app.compose.network.RecipeService
import com.app.compose.network.model.RecipeDTOMapper
import com.app.compose.persistence.dao.RecipeDao
import com.app.compose.persistence.model.mapper.RecipeEntityMapper
import com.app.compose.persistence.model.relations.RecipeWithIngredients

class RecipeRepositoryImpl (
    private val recipeService: RecipeService,
    private val mapper: RecipeDTOMapper,
    private val recipeDao: RecipeDao,
    private val entityMapper: RecipeEntityMapper
): RecipeRepository {
    private val token = "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
    override suspend fun search(page: Int, query: String): List<Recipe> {
        val count = recipeDao.countRecipes(page,query)
        val recipes = if(count >= 30) getRecipesFromCache(page, query)
        else getRecipesFromNetwork(page,query)
        cache(recipes, page)
        return recipes
    }

    override suspend fun get(id: Int): Recipe {
        recipeDao.getRecipe(id)?.let {
            return entityMapper.mapFromEntityModel(it)
        }
        return mapper.mapToDomainModel(recipeService.get(token = token, id))
    }
    private suspend fun getRecipesFromNetwork(page: Int, query: String): List<Recipe> {
        return mapper.toDomainList(
            recipeService.search(
                token = token,
                page = page,
                query = query).recipes
        )
    }

    private suspend fun cache(recipes: List<Recipe>, page: Int) {
        val entities: List<RecipeWithIngredients> = entityMapper.toEntityList(recipes)
        entities.forEach { entity ->
            val recipe = entity.recipeEntity
            recipe.page = page
            recipeDao.insertRecipes(recipe)
            recipeDao.insertIngredients(*entity.ingredients.toTypedArray())
        }
    }

    private suspend fun getRecipesFromCache(page: Int, query: String): List<Recipe> {
        return entityMapper.fromEntityList(recipeDao.getRecipes(page, query))
    }

}