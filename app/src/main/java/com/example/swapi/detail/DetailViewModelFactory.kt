package com.example.swapi.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.swapi.network.Character

class DetailViewModelFactory(
    private val character: Character,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(character, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}