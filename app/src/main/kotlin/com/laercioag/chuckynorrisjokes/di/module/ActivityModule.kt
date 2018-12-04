package com.laercioag.chuckynorrisjokes.di.module

import com.laercioag.chuckynorrisjokes.presentation.categories.CategoriesActivity
import com.laercioag.chuckynorrisjokes.presentation.categories.CategoriesFragment
import com.laercioag.chuckynorrisjokes.presentation.randomjoke.RandomJokeActivity
import com.laercioag.chuckynorrisjokes.presentation.randomjoke.RandomJokeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun bindCategoriesActivity(): CategoriesActivity

    @ContributesAndroidInjector
    abstract fun bindCategoriesFragment(): CategoriesFragment

    @ContributesAndroidInjector
    abstract fun bindRandomJokeActivity(): RandomJokeActivity

    @ContributesAndroidInjector
    abstract fun bindRandomJokeFragment(): RandomJokeFragment
}
