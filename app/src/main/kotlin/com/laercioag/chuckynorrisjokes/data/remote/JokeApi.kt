package com.laercioag.chuckynorrisjokes.data.remote

import com.laercioag.chuckynorrisjokes.data.dto.CategoryDto
import com.laercioag.chuckynorrisjokes.data.dto.JokeDto
import io.reactivex.Single

interface JokeApi {

    fun getRandomJokeFromCategory(categoryDto: CategoryDto): Single<JokeDto>
}
