package com.laercioag.chuckynorrisjokes.data.repository.impl

import com.laercioag.chuckynorrisjokes.data.dto.CategoryDto
import com.laercioag.chuckynorrisjokes.data.remote.CategoryApi
import com.laercioag.chuckynorrisjokes.data.repository.CategoryRepository
import io.reactivex.Single

class CategoryRepositoryImpl(val api: CategoryApi) :
    CategoryRepository {

    override fun getCategories(): Single<List<CategoryDto>> {
        return api.getCategories()
    }
}
