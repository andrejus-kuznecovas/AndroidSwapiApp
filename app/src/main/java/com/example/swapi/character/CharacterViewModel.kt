package com.example.swapi.character

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.swapi.network.Character

class CharacterViewModel(pagedListProvider: PagedListProvider<Character?>) : ViewModel() {

    private val _navigateToSelectedProperty = MutableLiveData<Character>()

    val navigateToSelectedProperty: LiveData<Character>
        get() = _navigateToSelectedProperty

    private val _searchCriteria = MutableLiveData<String?>()

    val searchCriteria: LiveData<String?>
        get() = _searchCriteria


    private val pagedListData = pagedListProvider.provide()

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

    fun changeSearchQuery(query: String?) {
        _searchCriteria.value = query
    }

    override fun onCleared() {
        super.onCleared()
    }
}