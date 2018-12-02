package com.laercioag.chuckynorrisjokes.data

import com.laercioag.chuckynorrisjokes.data.dto.CategoryDto
import com.laercioag.chuckynorrisjokes.data.dto.JokeDto
import java.io.File

class TestUtils {

    companion object {

        private fun genericString(): String {
            return "Generic String"
        }

        fun getTestCategoryDto(): CategoryDto {
            return "Test Category"
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

        fun generateCategoryDtoList(): List<CategoryDto> {
            return (0..10).map { getTestCategoryDto() }
        }

        fun getJson(path: String): String {
            val uri = this::class.java.classLoader!!.getResource(path)
            val file = File(uri.path)
            return String(file.readBytes())
        }
    }
}