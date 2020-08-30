package com.example.swapi.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.swapi.model.Character
import com.example.swapi.model.Film
import com.example.swapi.network.Swapi
import com.example.swapi.util.FilmUrlToFilmUtil.Companion.filmUrlListToFilmList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailViewModel(character: Character, app: Application) : AndroidViewModel(app) {
    private val _selectedCharacter = MutableLiveData<Character>()

    val selectedCharacter: LiveData<Character>
        get() = _selectedCharacter

    private val _allMovies = MutableLiveData<List<Film>>()

    val allMovies: LiveData<List<Film>>
        get() = _allMovies

    private var viewModelJob = Job()


    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val _filmsOfCharacter = MutableLiveData<List<Film>>()

    val filmsOfCharacter: LiveData<List<Film>>
        get() = _filmsOfCharacter

    init {
        getAllMovies()
        _selectedCharacter.value = character
    }

    private fun getAllMovies() {
        coroutineScope.launch {
            val allMoviesDeferred = Swapi.retrofitService.getFilmsAsync()
            try {
                _allMovies.value = allMoviesDeferred.await().results
                _filmsOfCharacter.value =
                    filmUrlListToFilmList(_selectedCharacter.value!!.films, _allMovies.value!!)
            } catch (e: Exception) {
                Log.e("DetailViewModel", "getAllMovies failed")
            }
        }
    }

    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }
}