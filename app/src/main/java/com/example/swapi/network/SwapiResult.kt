package com.example.swapi.network

import com.squareup.moshi.Json

data class SwapiResult(
    @Json(name = "results")
    val results: List<Character>
)