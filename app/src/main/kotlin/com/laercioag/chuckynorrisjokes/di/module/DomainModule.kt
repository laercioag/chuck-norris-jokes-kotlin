package com.laercioag.chuckynorrisjokes.di.module

import com.laercioag.chuckynorrisjokes.data.repository.CategoryRepository
import com.laercioag.chuckynorrisjokes.data.repository.JokeRepository
import com.laercioag.chuckynorrisjokes.domain.mapper.CategoryDtoMapper
import com.laercioag.chuckynorrisjokes.domain.mapper.JokeDtoMapper
import com.laercioag.chuckynorrisjokes.domain.usecase.GetCategoriesUseCase
import com.laercioag.chuckynorrisjokes.domain.usecase.GetRandomJokeUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideCategoryDtoMapper(): CategoryDtoMapper = CategoryDtoMapper()

    @Provides
    fun provideJokeDtoMapper(categoryDtoMapper: CategoryDtoMapper): JokeDtoMapper = JokeDtoMapper(categoryDtoMapper)

    @Provides
    fun provideGetCategoriesUseCase(
        categoryRepository: CategoryRepository,
        categoryDtoMapper: CategoryDtoMapper
    ): GetCategoriesUseCase = GetCategoriesUseCase(categoryRepository, categoryDtoMapper)

    @Provides
    fun provideGetRandomJokeUseCase(
        jokeRepository: JokeRepository,
        categoryDtoMapper: CategoryDtoMapper,
        jokeDtoMapper: JokeDtoMapper
    ): GetRandomJokeUseCase = GetRandomJokeUseCase(jokeRepository, categoryDtoMapper, jokeDtoMapper)
}
