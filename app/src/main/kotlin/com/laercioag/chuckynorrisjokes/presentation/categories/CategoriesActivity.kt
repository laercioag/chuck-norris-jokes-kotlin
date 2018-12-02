package com.laercioag.chuckynorrisjokes.presentation.categories

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.laercioag.chuckynorrisjokes.R
import com.laercioag.chuckynorrisjokes.data.repository.CategoryRepository
import com.laercioag.chuckynorrisjokes.data.repository.JokeRepository
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class CategoriesActivity : AppCompatActivity() {

    @Inject
    lateinit var categoryRepository: CategoryRepository

    @Inject
    lateinit var jokeRepository: JokeRepository

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_category)
        testData()
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

    private fun testData() {
        compositeDisposable.add(
            categoryRepository.getCategories()
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("Categories", it.toString())
                    it.map { category ->
                        jokeRepository.getRandomJokeFromCategory(category)
                            .subscribeOn(AndroidSchedulers.mainThread())
                            .subscribe({ joke ->
                                Log.d("JokeDto", joke.toString())
                            }, { throwable ->
                                Log.e("JokeDto", "Fail", throwable)
                            })
                    }
                }, {
                    Log.e("Categories", "Fail", it)
                })
        )
    }

}
