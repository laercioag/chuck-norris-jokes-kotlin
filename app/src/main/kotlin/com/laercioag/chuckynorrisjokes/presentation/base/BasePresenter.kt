package com.laercioag.chuckynorrisjokes.presentation.base

interface BasePresenter<V : BaseView> {

    fun attach(view: V)

    fun detach()
}
