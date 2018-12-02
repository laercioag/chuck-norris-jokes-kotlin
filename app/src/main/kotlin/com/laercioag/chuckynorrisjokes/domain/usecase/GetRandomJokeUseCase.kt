package com.laercioag.chuckynorrisjokes.domain.usecase

import com.laercioag.chuckynorrisjokes.data.repository.JokeRepository
import com.laercioag.chuckynorrisjokes.domain.entity.Category
import com.laercioag.chuckynorrisjokes.domain.entity.Joke
import com.laercioag.chuckynorrisjokes.domain.mapper.CategoryDtoMapper
import com.laercioag.chuckynorrisjokes.domain.mapper.JokeDtoMapper
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class GetRandomJokeUseCase @Inject constructor(
    private val repository: JokeRepository,
    private val categoryDtoMapper: CategoryDtoMapper,
    private val jokeDtoMapper: JokeDtoMapper
) {

    fun run(category: Category): Single<Joke> {
        val categoryDto = categoryDtoMapper.mapReverse(category)
        return repository.getRandomJokeFromCategory(categoryDto)
            .map(jokeDtoMapper::map)
            .observeOn(AndroidSchedulers.mainThread())
    }
}
