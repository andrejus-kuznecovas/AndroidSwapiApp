package com.example.swapi.character

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.swapi.datasource.SearchCharacterDataSource
import com.example.swapi.model.Character

class CharacterViewModel : ViewModel() {

    private val _navigateToSelectedProperty = MutableLiveData<Character>()

    val navigateToSelectedProperty: LiveData<Character>
        get() = _navigateToSelectedProperty

    private val _searchCriteria = MutableLiveData<String?>()

    val searchCriteria: LiveData<String?>
        get() = _searchCriteria

    private var pagedListProvider: PagedListProvider<Character?> = CharacterPagedListProvider(
        factory = SearchCharacterDataSource.factory(searchCriteria.value)
    )
    private var pagedListData: LiveData<PagedList<Character?>> = pagedListProvider.provide()

    init {
        _searchCriteria.value = null
    }

    var adapter = CharacterAdapter(CharacterAdapter.OnClickListener {
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
        pagedListProvider =
            CharacterPagedListProvider(SearchCharacterDataSource.factory(query))
        pagedListData = pagedListProvider.provide()
        //adapter.notifyDataSetChanged()
        _searchCriteria.value = query
    }

    override fun onCleared() {
        super.onCleared()
    }
}