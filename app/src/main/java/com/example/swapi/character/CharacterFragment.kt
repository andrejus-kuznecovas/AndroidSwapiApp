package com.example.swapi.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
        val binding = FragmentCharacterBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.characterGrid.adapter = CharacterAdapter(CharacterAdapter.OnClickListener {
            Toast.makeText(context, "Click happens XD", Toast.LENGTH_SHORT).show()
        })

        return binding.root
    }
}