package com.laercioag.chuckynorrisjokes.data.remote

import com.laercioag.chuckynorrisjokes.data.TestUtils.Companion.getJson
import com.laercioag.chuckynorrisjokes.data.remote.impl.CategoryApiImpl
import junit.framework.TestCase.assertEquals
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CategoryApiImplTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var api: CategoryApi

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

        api = CategoryApiImpl(service)
    }

    @Test
    fun testWhenGetCategoriesReturnsListOfCategories() {
        val path = "/categories"
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(getJson("json/category/categories.json"))
        mockWebServer.enqueue(mockResponse)
        api.getCategories().test()
            .assertNoErrors()
            .assertValueCount(1)
        val request = mockWebServer.takeRequest()
        assertEquals(path, request.path)
    }

    @Test
    fun testWhenGetCategoriesReturnsEmptyListOfCategories() {
        val path = "/categories"
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(getJson("json/category/categories_empty.json"))
        mockWebServer.enqueue(mockResponse)
        api.getCategories().test()
            .assertNoErrors()
            .assertValueCount(1)
        val request = mockWebServer.takeRequest()
        assertEquals(path, request.path)
    }

    @Test
    fun testWhenGetCategoriesReturnsError() {
        val path = "/categories"
        val mockResponse = MockResponse()
            .setResponseCode(500)
        mockWebServer.enqueue(mockResponse)
        api.getCategories().test()
            .assertNoValues()
        val request = mockWebServer.takeRequest()
        assertEquals(path, request.path)
    }

    @After
    fun after() {
        mockWebServer.shutdown()
    }

}
