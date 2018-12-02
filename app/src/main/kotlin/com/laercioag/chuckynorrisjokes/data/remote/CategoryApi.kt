package com.laercioag.chuckynorrisjokes.data.remote

import com.laercioag.chuckynorrisjokes.data.dto.CategoryDto
import io.reactivex.Single

interface CategoryApi {

    fun getCategories(): Single<List<CategoryDto>>
}
