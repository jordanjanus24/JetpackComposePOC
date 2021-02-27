package com.app.compose.di

import com.app.compose.network.RecipeService
import com.app.compose.network.model.RecipeDTOMapper
import com.app.compose.persistence.dao.RecipeDao
import com.app.compose.persistence.model.mapper.RecipeEntityMapper
import com.app.compose.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
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