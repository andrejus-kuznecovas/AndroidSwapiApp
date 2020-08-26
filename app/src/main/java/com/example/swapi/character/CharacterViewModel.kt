package com.example.swapi.character

import android.net.Uri
import androidx.lifecycle.*
import com.example.swapi.network.Character
import com.example.swapi.network.SwapiResult

class CharacterViewModel(pagedListProvider: PagedListProvider<Character?>) : ViewModel() {

    private val _swapiResult = MutableLiveData<SwapiResult>()

    val swapiResult: LiveData<SwapiResult>
        get() = _swapiResult

    private val _characterList = MutableLiveData<List<Character>>()

    val characterList: LiveData<List<Character>>
        get() = _characterList

    val nextPageUri = Transformations.map(_swapiResult) {
        Uri.parse(swapiResult.value?.nextPageUri)
    }

    val pagedListData = pagedListProvider.provide()
    val adapter = CharacterAdapter(CharacterAdapter.OnClickListener {})

    fun observePagedList(owner: LifecycleOwner) {
        pagedListData.observe(owner, Observer { adapter.submitList(it) })
    }


    init {

        //getCharacters(1)
    }

//    private fun getCharacters(page: Int) {
//        coroutineScope.launch {
//            var getPeopleDeferred = Swapi.retrofitService.getPeopleAsync(1)
//            try {
//                //status = loading
//                _swapiResult.value = getPeopleDeferred.await()
//                _characterList.value = _swapiResult.value!!.results
//            } catch (e: Exception) {
//
//            }
//        }
//    }
    
}