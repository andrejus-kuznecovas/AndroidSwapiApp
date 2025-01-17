package com.example.swapi.character

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.swapi.R
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

        viewModel.searchCriteria.observe(viewLifecycleOwner, {
            viewModel.observePagedList(this)
        })
        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, {
            if (it != null) {
                this.findNavController()
                    .navigate(CharacterFragmentDirections.actionCharacterFragmentToDetailFragment(it))
                viewModel.displayPropertyDetailsComplete()
            }
        })
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)

        val manager = context?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery("", false)
                searchItem.collapseActionView()
                viewModel.changeSearchQuery(query)
                Toast.makeText(context, "Looking for $query", Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
    }

    private fun provideViewModel() =
        CharacterViewModelFactory().create(CharacterViewModel::class.java)
}

