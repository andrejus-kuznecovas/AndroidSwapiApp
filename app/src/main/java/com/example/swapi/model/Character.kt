package com.example.swapi.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Character(
    val name: String,
    val height: String,
    val mass: String,
    @Json(name = "birth_year")
    val birthYear: String,
    val gender: String,
    val films: List<String>
) : Parcelable {}
