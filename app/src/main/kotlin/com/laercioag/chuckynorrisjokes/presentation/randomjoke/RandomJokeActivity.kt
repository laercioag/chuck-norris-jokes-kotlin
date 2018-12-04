package com.laercioag.chuckynorrisjokes.presentation.randomjoke

import android.os.Bundle
import androidx.fragment.app.transaction
import com.laercioag.chuckynorrisjokes.R
import com.laercioag.chuckynorrisjokes.domain.entity.Category
import com.laercioag.chuckynorrisjokes.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_categories.*

class RandomJokeActivity : BaseActivity() {

    companion object {
        private const val ARGUMENT_CATEGORY = "category"

        fun args(category: Category): Bundle {
            return Bundle().apply {
                putSerializable(ARGUMENT_CATEGORY, category)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_joke)
        setupToolbar()
        setupFragment()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun setupFragment() {
        val category: Category = intent.getSerializableExtra(ARGUMENT_CATEGORY) as Category
        var randomJokeFragment = supportFragmentManager.findFragmentById(R.id.content)
        if (randomJokeFragment == null) {
            randomJokeFragment = RandomJokeFragment.newInstance(category)
            supportFragmentManager.transaction {
                replace(R.id.content, randomJokeFragment)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
