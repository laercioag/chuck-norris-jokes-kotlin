package com.laercioag.chuckynorrisjokes.presentation.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<V : BaseView> {

    protected val compositeDisposable = CompositeDisposable()

    abstract fun attach(view: V)

    abstract fun detach()

    protected fun Disposable.disposeOnDetach() {
        compositeDisposable.add(this)
    }
}
