package com.app.compose.persistence.model

import androidx.room.*

@Entity(tableName = "Recipe")
data class RecipeEntity(
    @PrimaryKey @ColumnInfo(name = "recipe_id") val recipeId: Int? = null,
    @ColumnInfo(name = "title") val title: String? = null,
    @ColumnInfo(name = "publisher") val publisher: String? = null,
    @ColumnInfo(name = "featuredImage") val featuredImage: String? = null,
    @ColumnInfo(name = "rating") val rating: Int? = 0,
    @ColumnInfo(name = "sourceUrl") val sourceUrl: String? = null,
    @ColumnInfo(name = "description") val description: String? = null,
    @ColumnInfo(name = "cookingInstructions") val cookingInstructions: String? = null,
    @ColumnInfo(name = "dateAdded") val dateAdded: String? = null,
    @ColumnInfo(name = "dateUpdated") val dateUpdated: String? = null,
    @ColumnInfo(name = "page") var page: Int? = null
)