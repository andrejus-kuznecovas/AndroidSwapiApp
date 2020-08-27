package com.example.swapi.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.swapi.databinding.FragmentCharDetailBinding

class DetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        val binding = FragmentCharDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val selectedChar = DetailFragmentArgs.fromBundle(arguments!!).selectedCharacter

        val detalViewModelFactory = DetailViewModelFactory(selectedChar, application)
        val viewModel =
            ViewModelProvider(this, detalViewModelFactory).get(DetailViewModel::class.java)
        binding.detailViewModel = viewModel
        return binding.root
    }
}
