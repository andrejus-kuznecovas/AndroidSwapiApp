package com.example.swapi

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.swapi.character.CharacterAdapter
import com.example.swapi.network.Character


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Character>?) {
    val adapter = recyclerView.adapter as CharacterAdapter
    adapter.submitList(data)
}