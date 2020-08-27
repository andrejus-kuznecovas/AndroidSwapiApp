package com.example.swapi.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.swapi.network.Character

class DetailViewModel(character: Character, app: Application) : AndroidViewModel(app) {
    private val _selectedCharacter = MutableLiveData<Character>()

    val selectedCharacter: LiveData<Character>
        get() = _selectedCharacter

    init {
        _selectedCharacter.value = character
    }
}