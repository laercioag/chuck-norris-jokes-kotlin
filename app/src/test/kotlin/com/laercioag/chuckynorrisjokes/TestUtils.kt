package com.laercioag.chuckynorrisjokes

import com.laercioag.chuckynorrisjokes.data.dto.CategoryDto
import com.laercioag.chuckynorrisjokes.data.dto.JokeDto
import com.laercioag.chuckynorrisjokes.domain.entity.Category
import com.laercioag.chuckynorrisjokes.domain.entity.Joke
import java.io.File

class TestUtils {

    companion object {

        private fun genericString(): String {
            return "Generic String"
        }

        fun getTestCategoryDto(): CategoryDto {
            return "Test Category"
        }

        fun getTestCategory(): Category {
            return Category("Test Category")
        }

        fun getTestJokeDto(): JokeDto {
            return JokeDto(
                categories = generateCategoryDtoList(),
                iconUrl = genericString(),
                id = genericString(),
                url = genericString(),
                value = genericString()
            )
        }

        fun getTestJoke(): Joke {
            return Joke(
                categories = generateCategoryList(),
                iconUrl = genericString(),
                id = genericString(),
                url = genericString(),
                value = genericString()
            )
        }

        fun generateCategoryDtoList(): List<CategoryDto> {
            return (0..10).map { getTestCategoryDto() }
        }

        fun generateCategoryList(): List<Category> {
            return (0..10).map { getTestCategory() }
        }

        fun getJson(path: String): String {
            val uri = this::class.java.classLoader!!.getResource(path)
            val file = File(uri.path)
            return String(file.readBytes())
        }
    }
}