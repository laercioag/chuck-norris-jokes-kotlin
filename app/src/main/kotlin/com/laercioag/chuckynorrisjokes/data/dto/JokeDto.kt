package com.laercioag.chuckynorrisjokes.data.dto

import com.google.gson.annotations.SerializedName

data class JokeDto(
    @SerializedName("categoryDto")
    val categoryDto: List<CategoryDto> = listOf(),
    @SerializedName("icon_url")
    val iconUrl: String = "",
    @SerializedName("id")
    val id: String = "",
    @SerializedName("url")
    val url: String = "",
    @SerializedName("value")
    val value: String = ""
)
