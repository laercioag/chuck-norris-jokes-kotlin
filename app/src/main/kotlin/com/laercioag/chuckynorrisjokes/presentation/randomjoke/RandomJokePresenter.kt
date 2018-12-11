package com.laercioag.chuckynorrisjokes.presentation.randomjoke

import com.laercioag.chuckynorrisjokes.domain.entity.Category
import com.laercioag.chuckynorrisjokes.domain.entity.Joke
import com.laercioag.chuckynorrisjokes.domain.usecase.GetRandomJokeUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class RandomJokePresenter(private val getRandomJokeUseCase: GetRandomJokeUseCase) : RandomJokeContract.Presenter {

    private var view: RandomJokeContract.View? = null

    private val compositeDisposable = CompositeDisposable()

    override fun attach(view: RandomJokeContract.View) {
        this.view = view
    }

    override fun detach() {
        view = null
        compositeDisposable.clear()
    }

    override fun getRandomJoke(category: Category) {
        view?.showLoading()
        compositeDisposable.add(
            getRandomJokeUseCase(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    this::handleGetRandomJokeResult,
                    this::handleError
                )
        )
    }

    private fun handleGetRandomJokeResult(joke: Joke) {
        view?.hideLoading()
        view?.showJoke(joke)
    }

    private fun handleError(throwable: Throwable) {
        view?.hideLoading()
        view?.handleError(throwable)
    }

}
