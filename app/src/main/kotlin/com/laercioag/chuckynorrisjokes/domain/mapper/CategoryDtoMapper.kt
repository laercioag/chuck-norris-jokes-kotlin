package com.laercioag.chuckynorrisjokes.domain.mapper

import com.laercioag.chuckynorrisjokes.data.dto.CategoryDto
import com.laercioag.chuckynorrisjokes.domain.entity.Category

class CategoryDtoMapper : Mapper<CategoryDto, Category> {

    override fun map(param: CategoryDto): Category = with(param) {
        Category(this)
    }

    override fun mapReverse(param: Category): CategoryDto = with(param) {
        description
    }
}
