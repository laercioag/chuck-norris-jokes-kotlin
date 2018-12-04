package com.laercioag.chuckynorrisjokes.di.module

import com.laercioag.chuckynorrisjokes.domain.usecase.GetCategoriesUseCase
import com.laercioag.chuckynorrisjokes.domain.usecase.GetRandomJokeUseCase
import com.laercioag.chuckynorrisjokes.presentation.categories.CategoriesContract
import com.laercioag.chuckynorrisjokes.presentation.categories.CategoriesPresenter
import com.laercioag.chuckynorrisjokes.presentation.randomjoke.RandomJokeContract
import com.laercioag.chuckynorrisjokes.presentation.randomjoke.RandomJokePresenter
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {

    @Provides
    fun provideCategoriesPresenter(getCategoriesUseCase: GetCategoriesUseCase): CategoriesContract.Presenter =
        CategoriesPresenter(getCategoriesUseCase)

    @Provides
    fun provideRandomJokePresenter(getRandomJokeUseCase: GetRandomJokeUseCase): RandomJokeContract.Presenter =
        RandomJokePresenter(getRandomJokeUseCase)
}
