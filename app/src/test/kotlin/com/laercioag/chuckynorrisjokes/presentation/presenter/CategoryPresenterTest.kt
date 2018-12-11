package com.laercioag.chuckynorrisjokes.presentation.presenter

import com.laercioag.chuckynorrisjokes.TestUtils.Companion.generateCategoryList
import com.laercioag.chuckynorrisjokes.domain.mapper.CategoryDtoMapper
import com.laercioag.chuckynorrisjokes.domain.usecase.GetCategoriesUseCase
import com.laercioag.chuckynorrisjokes.presentation.categories.CategoriesContract
import com.laercioag.chuckynorrisjokes.presentation.categories.CategoriesPresenter
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class CategoryPresenterTest {

    private lateinit var getCategoriesUseCase: GetCategoriesUseCase
    private lateinit var categoriesPresenter: CategoriesPresenter
    private lateinit var mapper: CategoryDtoMapper
    private lateinit var view: CategoriesContract.View

    @Before
    fun before() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        mapper = CategoryDtoMapper()
        getCategoriesUseCase = mock(GetCategoriesUseCase::class.java)
        view = mock(CategoriesContract.View::class.java)
        categoriesPresenter = CategoriesPresenter(getCategoriesUseCase)
    }

    @Test
    fun testWhenGetCategoriesReturnsExpectedResult() {
        val categoryResult = generateCategoryList()
        `when`(getCategoriesUseCase()).thenReturn(Single.just(categoryResult))
        categoriesPresenter.attach(view)
        categoriesPresenter.getCategories()
        verify(view, times(1)).showLoading()
        verify(view, times(1)).hideLoading()
        verify(view, times(1)).showCategories(categoryResult)
    }

    @Test
    fun testWhenGetCategoriesReturnsError() {
        val error = RuntimeException()
        `when`(getCategoriesUseCase()).thenReturn(Single.error(error))
        categoriesPresenter.attach(view)
        categoriesPresenter.getCategories()
        verify(view, times(1)).showLoading()
        verify(view, times(1)).hideLoading()
        verify(view, times(1)).handleError(error)
    }
}