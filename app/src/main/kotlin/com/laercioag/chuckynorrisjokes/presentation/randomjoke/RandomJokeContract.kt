package com.laercioag.chuckynorrisjokes.presentation.randomjoke

import com.laercioag.chuckynorrisjokes.domain.entity.Category
import com.laercioag.chuckynorrisjokes.domain.entity.Joke
import com.laercioag.chuckynorrisjokes.presentation.base.BasePresenter
import com.laercioag.chuckynorrisjokes.presentation.base.BaseView

class RandomJokeContract {

    interface View : BaseView {

        fun showJoke(joke: Joke)
        
    }

    interface Presenter : BasePresenter<View> {

        fun getRandomJoke(category: Category)

    }
}
