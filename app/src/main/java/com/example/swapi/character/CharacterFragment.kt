package com.example.swapi.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.swapi.databinding.FragmentCharacterBinding

class CharacterFragment : Fragment() {

    lateinit var viewModel: CharacterViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCharacterBinding.inflate(inflater)
        binding.lifecycleOwner = this
        viewModel = provideViewModel()
        binding.viewModel = viewModel
        viewModel.observePagedList(this)

        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, {
            if (it != null) {
                this.findNavController()
                    .navigate(CharacterFragmentDirections.actionCharacterFragmentToDetailFragment(it))
                viewModel.displayPropertyDetailsComplete()
            }
        })

        return binding.root
    }

    private fun provideViewModel() = CharacterViewModelFactory(
        pagedListProvider = CharacterPagedListProvider(
            factory = CharacterDataSource.factory()
        )
    ).create(CharacterViewModel::class.java)
}