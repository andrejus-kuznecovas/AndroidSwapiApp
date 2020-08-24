package com.example.swapi.character

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.swapi.databinding.FragmentCharacterBinding

class CharacterFragment : Fragment() {

    private val viewModel: CharacterViewModel by lazy {
        ViewModelProvider(this).get(CharacterViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("CHAR INFO", "viewModel.swapiResult?.value.toString()")
        val binding = FragmentCharacterBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        Log.i("CHAR INFO", viewModel.swapiResult?.value.toString())
        return binding.root
    }
}