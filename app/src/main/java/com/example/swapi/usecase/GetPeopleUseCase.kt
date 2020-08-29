package com.example.swapi.usecase

import com.example.swapi.network.Character
import com.example.swapi.network.Swapi
import com.example.swapi.network.SwapiService

class GetPeopleUseCase(private val api: SwapiService) {

    suspend fun execute(page: Int): List<Character?> {
        val response = api.getPeopleAsync(page)
        return response.await().results
    }

    companion object {
        fun create() = GetPeopleUseCase(api = Swapi.retrofitService)
    }

}