package com.laercioag.chuckynorrisjokes.data.dto

import com.google.gson.annotations.SerializedName

data class JokeDto(
    val categories: List<CategoryDto> = listOf(),
    @SerializedName("icon_url")
    val iconUrl: String = "",
    val id: String = "",
    val url: String = "",
    val value: String = ""
)
