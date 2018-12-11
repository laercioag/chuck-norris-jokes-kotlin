package com.laercioag.chuckynorrisjokes.data.remote

import com.laercioag.chuckynorrisjokes.data.dto.CategoryDto
import io.reactivex.Single

class CategoryApi(private val service: RemoteService) {

    fun getCategories(): Single<List<CategoryDto>> = service.getCategories()

}
