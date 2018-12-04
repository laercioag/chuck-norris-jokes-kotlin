package com.laercioag.chuckynorrisjokes.di.module

import com.laercioag.chuckynorrisjokes.presentation.categories.CategoriesActivity
import com.laercioag.chuckynorrisjokes.presentation.categories.CategoriesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun bindCategoriesActivity(): CategoriesActivity

    @ContributesAndroidInjector
    abstract fun bindCategoriesFragment(): CategoriesFragment
}
