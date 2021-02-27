package com.app.compose.persistence.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.app.compose.persistence.model.Ingredient
import com.app.compose.persistence.model.RecipeEntity

data class RecipeWithIngredients(
    @Embedded val recipeEntity: RecipeEntity,
    @Relation(
        parentColumn = "recipe_id",
        entityColumn = "recipe_ingredient_id"
    )
    val ingredients: List<Ingredient>
)