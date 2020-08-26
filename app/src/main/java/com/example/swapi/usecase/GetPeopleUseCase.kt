package com.example.swapi.usecase

import com.example.swapi.network.Character
import com.example.swapi.network.Swapi
import com.example.swapi.network.SwapiService
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class GetPeopleUseCase(private val api: SwapiService) {

    fun execute(page: Int): Deferred<List<Character?>?> {
        return GlobalScope.async { transform(page) }
    }

    private suspend fun transform(page: Int): List<Character?> {
        val response = api.getPeopleAsync(page).await()

        return response.results ?: emptyList()
    }

    companion object {
        fun create() = GetPeopleUseCase(api = Swapi.retrofitService)
    }
}