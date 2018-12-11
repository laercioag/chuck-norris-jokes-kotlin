package com.laercioag.chuckynorrisjokes.data.repository

import com.laercioag.chuckynorrisjokes.TestUtils.Companion.generateCategoryDtoList
import com.laercioag.chuckynorrisjokes.data.remote.CategoryApi
import com.laercioag.chuckynorrisjokes.data.repository.impl.CategoryRepositoryImpl
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock


class CategoryRepositoryImplTest {

    private lateinit var api: CategoryApi
    private lateinit var repository: CategoryRepository

    @Before
    fun before() {
        api = mock(CategoryApi::class.java)
        repository = CategoryRepositoryImpl(api)

    }

    @Test
    fun testWhenGetCategoriesReturnsExpectedResults() {
        val categoryListResult = generateCategoryDtoList()
        `when`(api.getCategories()).thenReturn(Single.just(categoryListResult))
        repository.getCategories().test()
            .assertNoErrors()
            .assertValueCount(1)
            .assertValue { result -> result.size == categoryListResult.size }
            .assertValue(categoryListResult)
    }
}
