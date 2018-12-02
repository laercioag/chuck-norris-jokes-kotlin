package com.laercioag.chuckynorrisjokes.di.component

import com.laercioag.chuckynorrisjokes.di.module.ActivityModule
import com.laercioag.chuckynorrisjokes.di.module.AppModule
import com.laercioag.chuckynorrisjokes.di.module.DataModule
import com.laercioag.chuckynorrisjokes.di.module.DomainModule
import com.laercioag.chuckynorrisjokes.presentation.app.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        DataModule::class,
        DomainModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: App): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}
