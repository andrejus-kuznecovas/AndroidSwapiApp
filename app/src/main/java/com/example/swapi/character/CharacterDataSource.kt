package com.example.swapi.character

import androidx.paging.ItemKeyedDataSource
import com.example.swapi.network.Character
import kotlinx.coroutines.CoroutineScope

class CharacterDataSource(private val scope: CoroutineScope) :
    ItemKeyedDataSource<String, Character>() {
    override fun getKey(item: Character): String {
        TODO("Not yet implemented")
    }

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<Character>
    ) {
        TODO("Not yet implemented")
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<Character>) {
        TODO("Not yet implemented")
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<Character>) {
        TODO("Not yet implemented")
    }
}