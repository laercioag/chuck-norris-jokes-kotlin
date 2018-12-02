package com.laercioag.chuckynorrisjokes.domain.entity

data class Joke(
    val categories: List<Category>,
    val iconUrl: String,
    val id: String,
    val url: String,
    val value: String
)
