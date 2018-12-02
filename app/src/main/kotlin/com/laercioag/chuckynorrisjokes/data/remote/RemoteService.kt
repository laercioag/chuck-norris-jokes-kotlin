package com.laercioag.chuckynorrisjokes.data.remote

import com.laercioag.chuckynorrisjokes.data.dto.CategoryDto
import com.laercioag.chuckynorrisjokes.data.dto.JokeDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService {

    @GET("categories")
    fun getCategories(): Single<List<CategoryDto>>

    @GET("random")
    fun getRandomJokeFromCategory(@Query("categoryDto") category: CategoryDto): Single<JokeDto>
}
