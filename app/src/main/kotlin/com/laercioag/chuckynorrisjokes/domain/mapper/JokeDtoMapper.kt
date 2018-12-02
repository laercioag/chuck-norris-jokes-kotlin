package com.laercioag.chuckynorrisjokes.domain.mapper

import com.laercioag.chuckynorrisjokes.data.dto.JokeDto
import com.laercioag.chuckynorrisjokes.domain.entity.Joke
import javax.inject.Inject

class JokeDtoMapper @Inject constructor(private val categoryDtoMapper: CategoryDtoMapper) : Mapper<JokeDto, Joke> {

    override fun map(param: JokeDto): Joke = with(param) {
        Joke(
            categories = categoryDtoMapper.mapList(categories),
            id = id,
            url = url,
            iconUrl = iconUrl,
            value = value
        )
    }

    override fun mapReverse(param: Joke): JokeDto = with(param) {
        JokeDto(
            categories = categoryDtoMapper.mapListReverse(categories),
            id = id,
            url = url,
            iconUrl = iconUrl,
            value = value
        )
    }
}
