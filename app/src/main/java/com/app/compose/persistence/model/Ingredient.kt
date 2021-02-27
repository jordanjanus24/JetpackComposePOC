package com.app.compose.persistence.model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(foreignKeys = [ForeignKey(entity = RecipeEntity::class,
        parentColumns = ["recipe_id"],
    childColumns = ["recipe_ingredient_id"],
    onDelete = CASCADE)])
data class Ingredient(
    @PrimaryKey  @ColumnInfo(name = "id")
    val id: Int? = null,
    @ColumnInfo(name = "recipe_ingredient_id") val ingredientRecipeId: Int? = null,
    @ColumnInfo(name = "description")val description: String? = null
)