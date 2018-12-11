package com.laercioag.chuckynorrisjokes.data.remote

import com.laercioag.chuckynorrisjokes.data.dto.CategoryDto
import com.laercioag.chuckynorrisjokes.data.remote.RemoteService
import io.reactivex.Single

class CategoryApi(private val service: RemoteService) {

    fun getCategories(): Single<List<CategoryDto>> {
        return service.getCategories()
    }
}
