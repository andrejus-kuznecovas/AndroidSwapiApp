package com.example.swapi.character

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.swapi.network.Character
import com.example.swapi.network.Swapi
import com.example.swapi.network.SwapiResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {

    private val _swapiResult = MutableLiveData<SwapiResult>()

    val swapiResult: LiveData<SwapiResult>
        get() = _swapiResult

    private val _characterList = MutableLiveData<List<Character>>()

    val characterList: LiveData<List<Character>>
        get() = _characterList

    val nextPageUri = Transformations.map(_swapiResult) {
        Uri.parse(swapiResult.value?.nextPageUri)
    }


    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {

        getCharacters(1)
    }

    private fun getCharacters(page: Int) {
        coroutineScope.launch {
            var getPeopleDeferred = Swapi.retrofitService.getPeopleAsync(1)
            try {
                //status = loading
                _swapiResult.value = getPeopleDeferred.await()
                _characterList.value = _swapiResult.value!!.results
            } catch (e: Exception) {

            }
        }
    }

    override fun onCleared() {

        super.onCleared()
        viewModelJob.cancel()
    }
}