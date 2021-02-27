package com.app.compose.di

import com.app.compose.network.RecipeService
import com.app.compose.network.model.RecipeDTOMapper
import com.app.compose.persistence.dao.RecipeDao
import com.app.compose.persistence.model.mapper.RecipeEntityMapper
import com.app.compose.repository.RecipeRepository
import com.app.compose.repository.RecipeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRecipeRepository(
        recipeService: RecipeService,
        recipeMapper: RecipeDTOMapper,
        recipeDao: RecipeDao,
        entityMapper: RecipeEntityMapper
    ): RecipeRepository {
        return RecipeRepositoryImpl(
            recipeService = recipeService,
            mapper = recipeMapper,
            recipeDao = recipeDao,
            entityMapper = entityMapper
        )
    }
}