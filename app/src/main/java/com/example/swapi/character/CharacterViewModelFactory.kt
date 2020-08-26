package com.example.swapi.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.swapi.network.Character

@Suppress("UNCHECKED_CAST")
class CharacterViewModelFactory(private val pagedListProvider: PagedListProvider<Character?>) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CharacterViewModel(pagedListProvider) as T
    }
}