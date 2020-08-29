package com.example.swapi.network

import com.squareup.moshi.Json

data class SwapiResult(
    @Json(name = "count")
    val characterCount: Int,
    @Json(name = "next")
    val nextPageUri: String?,
    @Json(name = "previous")
    val previousPageUri: String?,
    @Json(name = "results")
    val results: List<Character>
)
