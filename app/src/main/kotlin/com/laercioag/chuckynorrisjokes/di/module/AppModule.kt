package com.laercioag.chuckynorrisjokes.di.module

import android.content.Context
import com.laercioag.chuckynorrisjokes.presentation.app.App
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideContext(app: App): Context = app.applicationContext

}
