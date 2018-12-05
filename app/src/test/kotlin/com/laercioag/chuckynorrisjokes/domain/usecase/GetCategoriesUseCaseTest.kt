package com.laercioag.chuckynorrisjokes.domain.usecase

import com.laercioag.chuckynorrisjokes.TestUtils.Companion.generateCategoryDtoList
import com.laercioag.chuckynorrisjokes.data.repository.CategoryRepository
import com.laercioag.chuckynorrisjokes.domain.mapper.CategoryDtoMapper
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class GetCategoriesUseCaseTest {

    private lateinit var repository: CategoryRepository
    private lateinit var mapper: CategoryDtoMapper
    private lateinit var getCategoriesUseCase: GetCategoriesUseCase

    @Before
    fun before() {
        repository = mock(CategoryRepository::class.java)
        mapper = CategoryDtoMapper()
        getCategoriesUseCase = GetCategoriesUseCase(repository, mapper)
    }

    @Test
    fun testWhenGetCategoriesUseCaseReturnsExpectedResult() {
        val categoryDtoList = generateCategoryDtoList()
        val categoryResult = categoryDtoList.map(mapper::map)
        `when`(repository.getCategories()).thenReturn(Single.just(categoryDtoList))
        getCategoriesUseCase.run().test()
            .assertNoErrors()
            .assertValueCount(1)
            .assertValue(categoryResult)
    }

    @Test
    fun testWhenGetCategoriesUseCaseReturnsError() {
        `when`(repository.getCategories()).thenReturn(Single.error(RuntimeException()))
        getCategoriesUseCase.run().test()
            .assertError(RuntimeException::class.java)
            .assertValueCount(0)
    }
}