package com.example.swapi.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
    val title: String,
    @Json(name = "episode_id")
    val episodeId: String,
    val director: String,
    @Json(name = "release_date")
    val releaseDate: String,
    @Json(name = "url")
    val filmUrl: String
) : Parcelable {}