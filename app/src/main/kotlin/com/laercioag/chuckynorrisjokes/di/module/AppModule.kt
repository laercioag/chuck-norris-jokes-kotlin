package com.laercioag.chuckynorrisjokes.di.module

import android.content.Context
import com.laercioag.chuckynorrisjokes.presentation.app.App
import dagger.Module

@Module
class AppModule {

    fun provideContext(app: App): Context = app.applicationContext

}
