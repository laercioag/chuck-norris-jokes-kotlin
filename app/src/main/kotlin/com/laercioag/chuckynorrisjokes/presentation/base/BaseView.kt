package com.laercioag.chuckynorrisjokes.presentation.base

interface BaseView {

    fun showLoading()

    fun hideLoading()

    fun handleError(throwable: Throwable)
}
