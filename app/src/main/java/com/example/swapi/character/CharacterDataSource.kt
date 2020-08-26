package com.example.swapi.character

import android.util.Log
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.swapi.network.Character
import com.example.swapi.network.Swapi
import com.example.swapi.usecase.GetPeopleUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

private const val FIRST_PAGE = 1

class CharacterDataSource(private val getPeopleUseCase: GetPeopleUseCase = GetPeopleUseCase.create()) :
    PageKeyedDataSource<Int, Character>() {
    private var dataSourceJob = Job()

    private val coroutineScope = CoroutineScope(dataSourceJob + Dispatchers.Main)
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Character>
    ) {
        coroutineScope.launch {
            try {
                val response = Swapi.retrofitService.getPeopleAsync(FIRST_PAGE)
                val characterList = response.await().results
                Log.i(
                    "CharacterDataSource",
                    "Is completed with " + characterList.toString()
                )
                callback.onResult(characterList, null, FIRST_PAGE.inc())

            } catch (e: Exception) {
                Log.e("CharacterDataSource", "loadInitial has failed")
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        coroutineScope.launch {
            try {
                val response = Swapi.retrofitService.getPeopleAsync(params.key)
                val characterList = response.await().results
                callback.onResult(characterList, params.key.dec())
            } catch (e: Exception) {
                Log.e("CharacterDataSource", "loadBefore has failed")
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        coroutineScope.launch {
            try {
                val response = Swapi.retrofitService.getPeopleAsync(params.key)
                val characterList = response.await().results
                callback.onResult(characterList, params.key.inc())
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

    override fun invalidate() {
        super.invalidate()
        dataSourceJob.cancel()
    }
}