package com.laercioag.chuckynorrisjokes.extensions

fun String.capitalizeWords(): String =
    split(" ").joinToString(" ") { toLowerCase().capitalize() }
