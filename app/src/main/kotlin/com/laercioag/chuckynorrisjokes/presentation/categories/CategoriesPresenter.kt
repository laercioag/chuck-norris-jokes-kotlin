package com.laercioag.chuckynorrisjokes.presentation.categories

import com.laercioag.chuckynorrisjokes.domain.entity.Category
import com.laercioag.chuckynorrisjokes.domain.usecase.GetCategoriesUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class CategoriesPresenter (private val getCategoriesUseCase: GetCategoriesUseCase) :
    CategoriesContract.Presenter {

    private var view: CategoriesContract.View? = null

    private val compositeDisposable = CompositeDisposable()

    private val categories: MutableList<Category> = mutableListOf()

    override fun getCategories() {
        view?.showLoading()
        compositeDisposable.add(
            getCategoriesUseCase.run().subscribe(
                this::handleGetCategoriesResult,
                this::handleGetCategoriesError
            )
        )
    }

    override fun getCachedCategories() {
        view?.showCategories(categories)
    }

    override fun attach(view: CategoriesContract.View) {
        this.view = view
    }

    override fun detach() {
        compositeDisposable.dispose()
        this.view = null
    }

    private fun handleGetCategoriesResult(result: List<Category>) {
        categories.clear()
        categories.addAll(result.sortedBy { category ->
            category.description
        })
        view?.hideLoading()
        view?.showCategories(categories)
    }

    private fun handleGetCategoriesError(throwable: Throwable) {
        view?.hideLoading()
        view?.handleError(throwable)
    }
}
