package com.laercioag.chuckynorrisjokes.data.remote.impl

import com.laercioag.chuckynorrisjokes.data.dto.CategoryDto
import com.laercioag.chuckynorrisjokes.data.remote.CategoryApi
import com.laercioag.chuckynorrisjokes.data.remote.RemoteService
import io.reactivex.Single

class CategoryApiImpl(private val service: RemoteService) :
    CategoryApi {

    override fun getCategories(): Single<List<CategoryDto>> {
        return service.getCategories()
    }
}
