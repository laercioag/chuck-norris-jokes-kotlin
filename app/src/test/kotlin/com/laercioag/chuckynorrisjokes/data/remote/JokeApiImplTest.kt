package com.laercioag.chuckynorrisjokes.data.remote

import com.laercioag.chuckynorrisjokes.data.TestUtils
import com.laercioag.chuckynorrisjokes.data.TestUtils.Companion.getTestCategoryDto
import com.laercioag.chuckynorrisjokes.data.remote.impl.JokeApiImpl
import junit.framework.TestCase
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class JokeApiImplTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var api: JokeApi

    @Before
    fun before() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(RemoteService::class.java)

        api = JokeApiImpl(service)
    }

    @Test
    fun testWhenGetRandomJokeFromCategoryReturnsJoke() {
        val category = getTestCategoryDto()
        val path = "/random?categoryDto=$category"
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(TestUtils.getJson("json/joke/joke.json"))
        mockWebServer.enqueue(mockResponse)
        api.getRandomJokeFromCategory(getTestCategoryDto()).test()
            .assertNoErrors()
            .assertValueCount(1)
        val request = mockWebServer.takeRequest()
        TestCase.assertEquals(path, request.path)
    }

    @Test
    fun testWhenGetRandomJokeFromCategoryReturnsError() {
        val category = getTestCategoryDto()
        val path = "/random?categoryDto=$category"
        val mockResponse = MockResponse()
            .setResponseCode(500)
        mockWebServer.enqueue(mockResponse)
        api.getRandomJokeFromCategory(category).test()
            .assertNoValues()
        val request = mockWebServer.takeRequest()
        TestCase.assertEquals(path, request.path)
    }

    @After
    fun after() {
        mockWebServer.shutdown()
    }

}
