package com.laercioag.chuckynorrisjokes.data.repository.impl

import com.laercioag.chuckynorrisjokes.data.dto.CategoryDto
import com.laercioag.chuckynorrisjokes.data.dto.JokeDto
import com.laercioag.chuckynorrisjokes.data.remote.JokeApi
import com.laercioag.chuckynorrisjokes.data.repository.JokeRepository
import io.reactivex.Single

class JokeRepositoryImpl(private val api: JokeApi) : JokeRepository {

    override fun getRandomJokeFromCategory(categoryDto: CategoryDto): Single<JokeDto> {
        return api.getRandomJokeFromCategory(categoryDto)
    }
}
