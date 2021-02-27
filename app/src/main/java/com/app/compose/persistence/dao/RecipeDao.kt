package com.app.compose.persistence.dao

import androidx.room.*
import com.app.compose.persistence.model.Ingredient
import com.app.compose.persistence.model.RecipeEntity
import com.app.compose.persistence.model.relations.RecipeWithIngredients

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(vararg recipeEntity: RecipeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredients(vararg ingredients: Ingredient)

    @Transaction
    @Query("SELECT * FROM Recipe WHERE page = :page AND title LIKE '%' || :query || '%' LIMIT 30")
    suspend fun getRecipes(page: Int, query: String): List<RecipeWithIngredients>

    @Transaction
    @Query("SELECT * FROM Recipe WHERE recipe_id = :id")
    suspend fun getRecipe(id: Int): RecipeWithIngredients?

    @Transaction
    @Query("SELECT COUNT(*) FROM Recipe WHERE page = :page AND title LIKE '%' || :query || '%'")
    suspend fun countRecipes(page: Int, query:String): Int

}