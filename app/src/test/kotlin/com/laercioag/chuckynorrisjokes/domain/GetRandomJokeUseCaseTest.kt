package com.laercioag.chuckynorrisjokes.domain

import com.laercioag.chuckynorrisjokes.data.TestUtils.Companion.getTestCategoryDto
import com.laercioag.chuckynorrisjokes.data.TestUtils.Companion.getTestJokeDto
import com.laercioag.chuckynorrisjokes.data.dto.CategoryDto
import com.laercioag.chuckynorrisjokes.data.dto.JokeDto
import com.laercioag.chuckynorrisjokes.data.repository.JokeRepository
import com.laercioag.chuckynorrisjokes.domain.entity.Category
import com.laercioag.chuckynorrisjokes.domain.entity.Joke
import com.laercioag.chuckynorrisjokes.domain.mapper.CategoryDtoMapper
import com.laercioag.chuckynorrisjokes.domain.mapper.JokeDtoMapper
import com.laercioag.chuckynorrisjokes.domain.usecase.GetRandomJokeUseCase
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class GetRandomJokeUseCaseTest {

    private lateinit var repository: JokeRepository
    private lateinit var categoryDtoMapper: CategoryDtoMapper
    private lateinit var jokeDtoMapper: JokeDtoMapper
    private lateinit var getRandomJokeUseCase: GetRandomJokeUseCase

    @Before
    fun before() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        repository = mock(JokeRepository::class.java)
        categoryDtoMapper = CategoryDtoMapper()
        jokeDtoMapper = JokeDtoMapper(categoryDtoMapper)
        getRandomJokeUseCase = GetRandomJokeUseCase(repository, categoryDtoMapper, jokeDtoMapper)
    }

    @Test
    fun testWhenGetCategoriesUseCaseReturnsExpectedResult() {
        val jokeDto: JokeDto = getTestJokeDto()
        val jokeResult: Joke = jokeDtoMapper.map(jokeDto)
        val categoryDto: CategoryDto = getTestCategoryDto()
        val category: Category = categoryDtoMapper.map(categoryDto)
        `when`(repository.getRandomJokeFromCategory(categoryDto)).thenReturn(Single.just(jokeDto))
        getRandomJokeUseCase.run(category).test()
            .assertNoErrors()
            .assertValueCount(1)
            .assertValue(jokeResult)
    }
}