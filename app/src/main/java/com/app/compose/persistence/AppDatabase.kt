package com.app.compose.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.compose.persistence.dao.RecipeDao
import com.app.compose.persistence.model.Ingredient
import com.app.compose.persistence.model.RecipeEntity

@Database(entities = [RecipeEntity::class, Ingredient::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}