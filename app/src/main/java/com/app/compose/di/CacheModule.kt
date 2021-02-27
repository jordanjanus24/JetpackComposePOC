package com.app.compose.di

import android.content.Context
import androidx.room.Room
import com.app.compose.persistence.AppDatabase
import com.app.compose.persistence.dao.RecipeDao
import com.app.compose.persistence.model.mapper.RecipeEntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideRecipeEntityMapper(): RecipeEntityMapper {
        return RecipeEntityMapper()
    }
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java, "app-db"
        ).allowMainThreadQueries().build()
    }

    @Provides
    fun provideRecipeDao(appDatabase: AppDatabase): RecipeDao {
        return appDatabase.recipeDao()
    }
}