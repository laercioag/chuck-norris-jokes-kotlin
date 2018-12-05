package com.laercioag.chuckynorrisjokes.presentation.categories

import com.laercioag.chuckynorrisjokes.domain.entity.Category
import com.laercioag.chuckynorrisjokes.presentation.base.BasePresenter
import com.laercioag.chuckynorrisjokes.presentation.base.BaseView

class CategoriesContract {

    interface View : BaseView {

        fun showCategories(categories: List<Category>)
    }

    interface Presenter : BasePresenter<View> {

        fun getCategories()
    }
}
