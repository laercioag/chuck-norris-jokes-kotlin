package com.laercioag.chuckynorrisjokes.data.remote

import com.laercioag.chuckynorrisjokes.data.dto.CategoryDto
import com.laercioag.chuckynorrisjokes.data.dto.JokeDto
import com.laercioag.chuckynorrisjokes.data.remote.RemoteService
import io.reactivex.Single

class JokeApi(private val service: RemoteService) {

    fun getRandomJokeFromCategory(categoryDto: CategoryDto): Single<JokeDto> {
        return service.getRandomJokeFromCategory(categoryDto)
    }
}
