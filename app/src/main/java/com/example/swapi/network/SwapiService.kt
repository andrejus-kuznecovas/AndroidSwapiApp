package com.example.swapi.network

import com.example.swapi.model.CharactersResult
import com.example.swapi.model.FilmResult
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "http://swapi.dev/api/"

enum class SwapiStatus { LOADING, ERROR, DONE }

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface SwapiService {
    @GET("people")
    fun getPeopleAsync(@Query("page") page: Int): Deferred<CharactersResult>

    @GET("people/")
    fun getPeopleSearchAsync(
        @Query("search") search: String?,
        @Query("page") page: Int
    ): Deferred<CharactersResult>

    @GET("films")
    fun getFilmsAsync(): Deferred<FilmResult>
}

object Swapi {
    val retrofitService: SwapiService by lazy { retrofit.create(SwapiService::class.java) }
}