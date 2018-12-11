package com.laercioag.chuckynorrisjokes.data.remote

import com.laercioag.chuckynorrisjokes.TestUtils
import com.laercioag.chuckynorrisjokes.TestUtils.Companion.getTestCategoryDto
import junit.framework.TestCase
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URLEncoder

class JokeApiTest {

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

        api = JokeApi(service)
    }

    @Test
    fun testWhenGetRandomJokeFromCategoryReturnsJoke() {
        val category = getTestCategoryDto()
        val path = "/random?category=" + URLEncoder.encode(category, "UTF-8")
            .replace("+", "%20")
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
        val path = "/random?category=" + URLEncoder.encode(category, "UTF-8")
            .replace("+", "%20")
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
