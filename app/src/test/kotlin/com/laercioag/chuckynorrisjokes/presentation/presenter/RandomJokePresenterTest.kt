package com.laercioag.chuckynorrisjokes.presentation.presenter

import com.laercioag.chuckynorrisjokes.TestUtils.Companion.getTestCategory
import com.laercioag.chuckynorrisjokes.TestUtils.Companion.getTestJoke
import com.laercioag.chuckynorrisjokes.domain.entity.Category
import com.laercioag.chuckynorrisjokes.domain.entity.Joke
import com.laercioag.chuckynorrisjokes.domain.usecase.GetRandomJokeUseCase
import com.laercioag.chuckynorrisjokes.presentation.randomjoke.RandomJokeContract
import com.laercioag.chuckynorrisjokes.presentation.randomjoke.RandomJokePresenter
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class RandomJokePresenterTest {

    private lateinit var getRandomJokeUseCase: GetRandomJokeUseCase
    private lateinit var categoriesPresenter: RandomJokePresenter
    private lateinit var view: RandomJokeContract.View

    @Before
    fun before() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        getRandomJokeUseCase = mock(GetRandomJokeUseCase::class.java)
        view = mock(RandomJokeContract.View::class.java)
        categoriesPresenter = RandomJokePresenter(getRandomJokeUseCase)
    }

    @Test
    fun testWhenGetCategoriesReturnsExpectedResult() {
        val jokeResult: Joke = getTestJoke()
        val category: Category = getTestCategory()
        Mockito.`when`(getRandomJokeUseCase(category)).thenReturn(Single.just(jokeResult))
        categoriesPresenter.attach(view)
        categoriesPresenter.getRandomJoke(category)
        Mockito.verify(view, Mockito.times(1)).showLoading()
        Mockito.verify(view, Mockito.times(1)).hideLoading()
        Mockito.verify(view, Mockito.times(1)).showJoke(jokeResult)
    }

    @Test
    fun testWhenGetCategoriesReturnsError() {
        val error = RuntimeException()
        val category: Category = getTestCategory()
        Mockito.`when`(getRandomJokeUseCase(category)).thenReturn(Single.error(error))
        categoriesPresenter.attach(view)
        categoriesPresenter.getRandomJoke(category)
        Mockito.verify(view, Mockito.times(1)).showLoading()
        Mockito.verify(view, Mockito.times(1)).hideLoading()
        Mockito.verify(view, Mockito.times(1)).handleError(error)
    }
}