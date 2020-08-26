package com.example.swapi.character

import android.util.Log
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.swapi.network.Character
import com.example.swapi.network.Swapi
import com.example.swapi.usecase.GetPeopleUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

private const val FIRST_PAGE = 1

class CharacterDataSource(private val getPeopleUseCase: GetPeopleUseCase = GetPeopleUseCase.create()) :
    PageKeyedDataSource<Int, Character>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Character>
    ) {
        GlobalScope.async {
            try {
                val response = Swapi.retrofitService.getPeopleAsync(FIRST_PAGE)
                when {
                    response.isCompleted -> {
                        val characterList = response.await().results
                        callback.onResult(characterList, null, FIRST_PAGE.inc())
                    }
                    else -> Log.e("CharacterDataSource", "loadInitial else has failed")
                }
            } catch (e: Exception) {
                Log.e("CharacterDataSource", "loadInitial has failed")
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        GlobalScope.async {
            try {
                val response = Swapi.retrofitService.getPeopleAsync(params.key)
                when {
                    response.isCompleted -> {
                        val characterList = response.await().results
                        callback.onResult(characterList, params.key.dec())
                    }
                    else -> Log.e("CharacterDataSource", "loadBefore else has failed")
                }
            } catch (e: Exception) {
                Log.e("CharacterDataSource", "loadBefore has failed")
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        GlobalScope.async {
            try {
                val response = Swapi.retrofitService.getPeopleAsync(params.key)
                when {
                    response.isCompleted -> {
                        val characterList = response.await().results
                        callback.onResult(characterList, params.key.inc())
                    }
                    else -> Log.e("CharacterDataSource", "loadAfter else has failed")
                }
            } catch (e: Exception) {
                Log.e("CharacterDataSource", "loadAfter has failed")
            }
        }
    }

    companion object {

        fun factory() = object : DataSource.Factory<Int, Character?>() {
            override fun create() = CharacterDataSource()
        }
    }
}