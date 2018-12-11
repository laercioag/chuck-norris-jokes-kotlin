package com.laercioag.chuckynorrisjokes.domain.usecase

import com.laercioag.chuckynorrisjokes.TestUtils.Companion.getTestCategoryDto
import com.laercioag.chuckynorrisjokes.TestUtils.Companion.getTestJokeDto
import com.laercioag.chuckynorrisjokes.data.dto.CategoryDto
import com.laercioag.chuckynorrisjokes.data.dto.JokeDto
import com.laercioag.chuckynorrisjokes.data.repository.JokeRepository
import com.laercioag.chuckynorrisjokes.domain.entity.Category
import com.laercioag.chuckynorrisjokes.domain.entity.Joke
import com.laercioag.chuckynorrisjokes.domain.mapper.CategoryDtoMapper
import com.laercioag.chuckynorrisjokes.domain.mapper.JokeDtoMapper
import io.reactivex.Single
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
        getRandomJokeUseCase(category).test()
            .assertNoErrors()
            .assertValueCount(1)
            .assertValue(jokeResult)
    }

    @Test
    fun testWhenGetCategoriesUseCaseReturnsError() {
        val categoryDto: CategoryDto = getTestCategoryDto()
        val category: Category = categoryDtoMapper.map(categoryDto)
        `when`(repository.getRandomJokeFromCategory(categoryDto)).thenReturn(Single.error(RuntimeException()))
        getRandomJokeUseCase(category).test()
            .assertError(RuntimeException::class.java)
            .assertValueCount(0)
    }
}