package com.laercioag.chuckynorrisjokes.presentation.categories

import android.os.Bundle
import androidx.fragment.app.transaction
import com.laercioag.chuckynorrisjokes.R
import com.laercioag.chuckynorrisjokes.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_categories.*


class CategoriesActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)
        setupToolbar()
        setupFragment()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
    }

    private fun setupFragment() {
        var categoriesFragment = supportFragmentManager.findFragmentById(R.id.content)
        if (categoriesFragment == null) {
            categoriesFragment = CategoriesFragment()
            supportFragmentManager.transaction {
                replace(R.id.content, categoriesFragment)
            }
        }
    }

}
