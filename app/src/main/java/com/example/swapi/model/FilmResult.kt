package com.example.swapi.model

import com.squareup.moshi.Json

data class FilmResult(
    @Json(name = "count")
    val filmCount: Int,
    @Json(name = "next")
    val nextPageUri: String?,
    @Json(name = "previous")
    val previousPageUri: String?,
    @Json(name = "results")
    val results: List<Film>
)
