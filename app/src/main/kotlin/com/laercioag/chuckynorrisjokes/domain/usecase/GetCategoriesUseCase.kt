package com.laercioag.chuckynorrisjokes.domain.usecase

import com.laercioag.chuckynorrisjokes.data.repository.CategoryRepository
import com.laercioag.chuckynorrisjokes.domain.entity.Category
import com.laercioag.chuckynorrisjokes.domain.mapper.CategoryDtoMapper
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: CategoryRepository,
    private val mapper: CategoryDtoMapper
) {

    fun run(): Single<List<Category>> {
        return repository.getCategories()
            .map(mapper::mapList)
            .observeOn(AndroidSchedulers.mainThread())
    }
}
