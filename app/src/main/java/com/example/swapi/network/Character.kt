package com.example.swapi.network

import com.squareup.moshi.Json

data class Character(
    val name: String,
    val height: String,
    val mass: String,
    @Json(name = "birth_year")
    val birthYear: String,
    val gender: String
) {}
