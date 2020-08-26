package com.example.swapi

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swapi.character.CharacterViewModel


//@BindingAdapter("listData")
//fun bindRecyclerView(recyclerView: RecyclerView, data: List<Character>?) {
//    val adapter = recyclerView.adapter as CharacterAdapter
//    adapter.submitList(data)
//}

@BindingAdapter("android:adapterSetup")
fun setupRecyclerViewAdapter(view: RecyclerView, viewModel: CharacterViewModel) {

    view.layoutManager = GridLayoutManager(view.context, 2)
    view.adapter = viewModel.adapter
}