package com.laercioag.chuckynorrisjokes.data.repository

import com.laercioag.chuckynorrisjokes.data.dto.CategoryDto
import com.laercioag.chuckynorrisjokes.data.dto.JokeDto
import io.reactivex.Single

interface JokeRepository {

    fun getRandomJokeFromCategory(categoryDto: CategoryDto): Single<JokeDto>
}
