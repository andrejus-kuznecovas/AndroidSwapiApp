package com.example.swapi.character

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getSwapiResult()
    }

    private fun getSwapiResult() {
        coroutineScope.launch {
            var getPeopleDeferred = Swapi.retrofitService.getPeopleAsync()
            try {
                //status = loading
                Log.i("CHAR getSwapiResult", getPeopleDeferred.await().toString())
                _swapiResult.value = getPeopleDeferred.await()

            } catch (e: Exception) {

            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}