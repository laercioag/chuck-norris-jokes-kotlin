package com.laercioag.chuckynorrisjokes.domain

import com.laercioag.chuckynorrisjokes.data.TestUtils.Companion.generateCategoryDtoList
import com.laercioag.chuckynorrisjokes.data.repository.CategoryRepository
import com.laercioag.chuckynorrisjokes.domain.mapper.CategoryDtoMapper
import com.laercioag.chuckynorrisjokes.domain.usecase.GetCategoriesUseCase
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
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
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
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
}