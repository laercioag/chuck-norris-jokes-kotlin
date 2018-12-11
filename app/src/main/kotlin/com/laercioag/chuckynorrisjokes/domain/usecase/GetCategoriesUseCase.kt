package com.laercioag.chuckynorrisjokes.domain.usecase

import com.laercioag.chuckynorrisjokes.data.repository.CategoryRepository
import com.laercioag.chuckynorrisjokes.domain.entity.Category
import com.laercioag.chuckynorrisjokes.domain.mapper.CategoryDtoMapper
import io.reactivex.Single

class GetCategoriesUseCase(
    private val repository: CategoryRepository,
    private val mapper: CategoryDtoMapper
) {

    fun run(): Single<List<Category>> =
        repository.getCategories()
            .map { mapper.mapList(it).sortedBy { category -> category.description } }

}
