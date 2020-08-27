package com.example.swapi.character

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.swapi.network.Character

class CharacterViewModel(pagedListProvider: PagedListProvider<Character?>) : ViewModel() {
//
//    private val _swapiResult = MutableLiveData<SwapiResult>()
//
//    val swapiResult: LiveData<SwapiResult>
//        get() = _swapiResult
//
//    private val _characterList = MutableLiveData<List<Character>>()
//
//    val characterList: LiveData<List<Character>>
//        get() = _characterList

    private val _navigateToSelectedProperty = MutableLiveData<Character>()

    val navigateToSelectedProperty: LiveData<Character>
        get() = _navigateToSelectedProperty

    val pagedListData = pagedListProvider.provide()
    val adapter = CharacterAdapter(CharacterAdapter.OnClickListener {
        this.displayPropertyDetails(it)
    })

    fun observePagedList(owner: LifecycleOwner) {
        pagedListData.observe(owner, { adapter.submitList(it) })
    }


    fun displayPropertyDetails(character: Character?) {
        _navigateToSelectedProperty.value = character
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

}