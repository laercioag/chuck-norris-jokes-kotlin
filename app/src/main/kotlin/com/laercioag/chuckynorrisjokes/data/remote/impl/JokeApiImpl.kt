package com.laercioag.chuckynorrisjokes.data.remote.impl

import com.laercioag.chuckynorrisjokes.data.dto.CategoryDto
import com.laercioag.chuckynorrisjokes.data.dto.JokeDto
import com.laercioag.chuckynorrisjokes.data.remote.JokeApi
import com.laercioag.chuckynorrisjokes.data.remote.RemoteService
import io.reactivex.Single
import javax.inject.Inject

class JokeApiImpl @Inject constructor(private val service: RemoteService) :
    JokeApi {

    override fun getRandomJokeFromCategory(categoryDto: CategoryDto): Single<JokeDto> {
        return service.getRandomJokeFromCategory(categoryDto)
    }
}
