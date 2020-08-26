package com.example.swapi.character

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.swapi.network.Character
import com.example.swapi.network.Swapi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

private const val FIRST_PAGE = 1

class CharacterDataSource(private val scope: CoroutineScope) :
    PageKeyedDataSource<Int, Character>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Character>
    ) {
        scope.launch {
            try {
                val response = Swapi.retrofitService.getPeopleAsync(FIRST_PAGE)
                when {
                    response.isCompleted -> {
                        val characterList = response.await().results
                        callback.onResult(characterList, null, FIRST_PAGE.inc())
                    }
                }
            } catch (e: Exception) {
                Log.e("CharacterDataSource", "loadInitial has failed")
            }
//            var getPeopleDeferred = Swapi.retrofitService.getPeopleAsync(1)
//            try {
//                //status = loading
//                _swapiResult.value = getPeopleDeferred.await()
//                _characterList.value = _swapiResult.value!!.results
//            } catch (e: Exception) {
//
//            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        TODO("Not yet implemented")
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        scope.launch {
            try {
                val response = Swapi.retrofitService.getPeopleAsync(params.key)
                when {
                    response.isCompleted -> {
                        val characterList = response.await().results
                        callback.onResult(characterList, params.key.inc())
                    }
                }
            } catch (e: Exception) {
                Log.e("CharacterDataSource", "loadAfter has failed")
            }
        }
    }

    override fun invalidate() {
        super.invalidate()
        scope.cancel()
    }
}