package com.example.swapi.usecase

import com.example.swapi.model.Character
import com.example.swapi.network.Swapi
import com.example.swapi.network.SwapiService

class GetSearchedPeopleUseCase(private val api: SwapiService) {
    suspend fun execute(searchCriteria: String?, page: Int): List<Character?> {
        val response = api.getPeopleSearchAsync(searchCriteria, page)
        return response.await().results
    }

    companion object {
        fun create() = GetSearchedPeopleUseCase(api = Swapi.retrofitService)
    }
}