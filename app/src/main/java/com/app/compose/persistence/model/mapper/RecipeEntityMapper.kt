package com.app.compose.persistence.model.mapper

import com.app.compose.domain.model.Recipe
import com.app.compose.domain.util.EntityMapper
import com.app.compose.network.model.RecipeDTO
import com.app.compose.persistence.model.Ingredient
import com.app.compose.persistence.model.RecipeEntity
import com.app.compose.persistence.model.relations.RecipeWithIngredients

class RecipeEntityMapper: EntityMapper<Recipe,RecipeWithIngredients> {
    override fun mapToEntityModel(model: Recipe): RecipeWithIngredients {
        return RecipeWithIngredients(
            recipeEntity = RecipeEntity(
                recipeId = model.id,
                title = model.title,
                publisher = model.publisher,
                featuredImage = model.featuredImage,
                rating = model.rating,
                sourceUrl = model.sourceUrl,
                description = model.description,
                cookingInstructions = model.cookingInstructions,
                dateAdded = model.dateAdded,
                dateUpdated = model.dateUpdated
            ),
            ingredients = model.ingredients.map { ingredient ->
                Ingredient(ingredientRecipeId = model.id,
                    description = ingredient)
            }
        )
    }

    override fun mapFromEntityModel(entity: RecipeWithIngredients): Recipe {
        val recipe = entity.recipeEntity
        val ingredients: List<String> = entity.ingredients.map { ingredient -> ingredient.description!! }
        return Recipe(
            id = recipe.recipeId,
            title = recipe.title,
            publisher = recipe.publisher,
            featuredImage = recipe.featuredImage,
            rating = recipe.rating,
            sourceUrl = recipe.sourceUrl,
            description = recipe.description,
            cookingInstructions = recipe.cookingInstructions,
            ingredients = ingredients,
            dateAdded = recipe.dateAdded,
            dateUpdated = recipe.dateUpdated
        )
    }

    fun toEntityList(initial: List<Recipe>): List<RecipeWithIngredients>{
        return initial.map { mapToEntityModel(it) }
    }

    fun fromEntityList(initial: List<RecipeWithIngredients>): List<Recipe>{
        return initial.map { mapFromEntityModel(it) }
    }
}