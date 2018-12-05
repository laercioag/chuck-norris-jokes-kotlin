package com.laercioag.chuckynorrisjokes.presentation.categories

import com.laercioag.chuckynorrisjokes.domain.entity.Category
import com.laercioag.chuckynorrisjokes.domain.usecase.GetCategoriesUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CategoriesPresenter(private val getCategoriesUseCase: GetCategoriesUseCase) :
    CategoriesContract.Presenter {

    private var view: CategoriesContract.View? = null

    private val compositeDisposable = CompositeDisposable()

    override fun getCategories() {
        view?.showLoading()
        compositeDisposable.add(
            getCategoriesUseCase.run()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    this::handleGetCategoriesResult,
                    this::handleGetCategoriesError
                )
        )
    }

    override fun attach(view: CategoriesContract.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
        compositeDisposable.clear()
    }

    private fun handleGetCategoriesResult(categories: List<Category>) {
        view?.hideLoading()
        view?.showCategories(categories)
    }

    private fun handleGetCategoriesError(throwable: Throwable) {
        view?.hideLoading()
        view?.handleError(throwable)
    }
}
