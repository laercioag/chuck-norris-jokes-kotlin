package com.laercioag.chuckynorrisjokes.di.module

import com.laercioag.chuckynorrisjokes.data.remote.CategoryApi
import com.laercioag.chuckynorrisjokes.data.remote.JokeApi
import com.laercioag.chuckynorrisjokes.data.remote.impl.CategoryApiImpl
import com.laercioag.chuckynorrisjokes.data.remote.impl.JokeApiImpl
import com.laercioag.chuckynorrisjokes.data.remote.RemoteService
import com.laercioag.chuckynorrisjokes.data.repository.CategoryRepository
import com.laercioag.chuckynorrisjokes.data.repository.JokeRepository
import com.laercioag.chuckynorrisjokes.data.repository.impl.CategoryRepositoryImpl
import com.laercioag.chuckynorrisjokes.data.repository.impl.JokeRepositoryImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(Level.BODY))
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(client)
            .baseUrl("https://api.chucknorris.io/jokes/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideRemoteService(retrofit: Retrofit): RemoteService =
        retrofit.create(RemoteService::class.java)

    @Provides
    @Singleton
    fun provideCategoryApi(service: RemoteService): CategoryApi =
        CategoryApiImpl(service)

    @Provides
    @Singleton
    fun provideCategoryRepository(api: CategoryApi): CategoryRepository =
        CategoryRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideJokeApi(service: RemoteService): JokeApi =
        JokeApiImpl(service)

    @Provides
    @Singleton
    fun provideJokeRepository(api: JokeApi): JokeRepository =
        JokeRepositoryImpl(api)

}
