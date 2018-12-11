package com.laercioag.chuckynorrisjokes.data.repository

import com.laercioag.chuckynorrisjokes.TestUtils.Companion.getTestCategoryDto
import com.laercioag.chuckynorrisjokes.TestUtils.Companion.getTestJokeDto
import com.laercioag.chuckynorrisjokes.data.remote.JokeApi
import com.laercioag.chuckynorrisjokes.data.repository.impl.JokeRepositoryImpl
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class JokeDtoRepositoryImplTest {

    private lateinit var api: JokeApi
    private lateinit var repository: JokeRepository

    @Before
    fun before() {
        api = Mockito.mock(JokeApi::class.java)
        repository = JokeRepositoryImpl(api)
    }

    @Test
    fun testWhenGetRandomJokeFromCategoryReturnsExpectedResults() {
        val category = getTestCategoryDto()
        val jokeResult = getTestJokeDto()
        `when`(api.getRandomJokeFromCategory(category))
            .thenReturn(Single.just(jokeResult))
        repository.getRandomJokeFromCategory(category).test()
            .assertNoErrors()
            .assertValueCount(1)
            .assertValue(jokeResult)
    }
}
