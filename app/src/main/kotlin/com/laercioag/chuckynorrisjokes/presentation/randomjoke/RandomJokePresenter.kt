package com.laercioag.chuckynorrisjokes.presentation.randomjoke

import com.laercioag.chuckynorrisjokes.domain.entity.Category
import com.laercioag.chuckynorrisjokes.domain.entity.Joke
import com.laercioag.chuckynorrisjokes.domain.usecase.GetRandomJokeUseCase
import io.reactivex.disposables.CompositeDisposable


class RandomJokePresenter(private val getRandomJokeUseCase: GetRandomJokeUseCase) : RandomJokeContract.Presenter {

    private var view: RandomJokeContract.View? = null

    private val compositeDisposable = CompositeDisposable()

    override fun attach(view: RandomJokeContract.View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }

    override fun getRandomJoke(category: Category) {
        view?.showLoading()
        compositeDisposable.add(
            getRandomJokeUseCase.run(category).subscribe(
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
