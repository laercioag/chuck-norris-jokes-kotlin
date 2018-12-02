package com.laercioag.chuckynorrisjokes.presentation.categories

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.laercioag.chuckynorrisjokes.R
import com.laercioag.chuckynorrisjokes.domain.usecase.GetCategoriesUseCase
import com.laercioag.chuckynorrisjokes.domain.usecase.GetRandomJokeUseCase
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class CategoriesActivity : AppCompatActivity() {

    @Inject
    lateinit var getCategoriesUseCase: GetCategoriesUseCase

    @Inject
    lateinit var getRandomJokeUseCase: GetRandomJokeUseCase

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
            getCategoriesUseCase.run().subscribe({
                Log.d("Categories", it.toString())
                it.map { category ->
                    getRandomJokeUseCase.run(category)
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
