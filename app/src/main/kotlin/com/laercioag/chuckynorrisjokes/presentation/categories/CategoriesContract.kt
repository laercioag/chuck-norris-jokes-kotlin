package com.laercioag.chuckynorrisjokes.presentation.categories

import com.laercioag.chuckynorrisjokes.domain.entity.Category
import com.laercioag.chuckynorrisjokes.presentation.base.BasePresenter
import com.laercioag.chuckynorrisjokes.presentation.base.BaseView

class CategoriesContract {

    interface View : BaseView {

        fun showCategories(categories: List<Category>)

    }

    abstract class Presenter : BasePresenter<View>() {

        abstract fun getCategories()

    }
}
