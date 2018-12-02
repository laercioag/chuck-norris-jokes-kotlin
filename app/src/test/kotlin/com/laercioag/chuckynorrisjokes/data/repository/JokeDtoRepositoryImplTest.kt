package com.laercioag.chuckynorrisjokes.data.repository

import com.laercioag.chuckynorrisjokes.data.TestUtils.Companion.getTestCategory
import com.laercioag.chuckynorrisjokes.data.TestUtils.Companion.getTestJokeDto
import com.laercioag.chuckynorrisjokes.data.remote.JokeApi
import com.laercioag.chuckynorrisjokes.data.remote.impl.JokeApiImpl
import com.laercioag.chuckynorrisjokes.data.repository.impl.JokeRepositoryImpl
import io.reactivex.Single
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class JokeDtoRepositoryImplTest {

    private lateinit var api: JokeApi
    private lateinit var repository: JokeRepository

    @Before
    fun before() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        api = Mockito.mock(JokeApiImpl::class.java)
        repository = JokeRepositoryImpl(api)

    }

    @Test
    fun testWhenGetRandomJokeFromCategoryReturnsExpectedResults() {
        val category = getTestCategory()
        val jokeResult = getTestJokeDto()
        `when`(api.getRandomJokeFromCategory(category))
            .thenReturn(Single.just(jokeResult))
        repository.getRandomJokeFromCategory(category).test()
            .assertNoErrors()
            .assertValueCount(1)
            .assertValue(jokeResult)
    }
}
