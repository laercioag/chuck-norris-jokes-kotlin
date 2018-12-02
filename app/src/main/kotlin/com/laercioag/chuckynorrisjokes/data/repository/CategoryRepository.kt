package com.laercioag.chuckynorrisjokes.data.repository

import com.laercioag.chuckynorrisjokes.data.dto.CategoryDto
import io.reactivex.Single

interface CategoryRepository {

    fun getCategories(): Single<List<CategoryDto>>
}
