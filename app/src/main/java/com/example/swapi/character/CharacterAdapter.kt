package com.example.swapi.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.swapi.databinding.GridViewItemBinding
import com.example.swapi.model.Character

class CharacterAdapter(
    private val onClickListener: OnClickListener
) :
    PagedListAdapter<Character, CharacterAdapter.CharacterViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterViewHolder {
        return CharacterViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(character)
        }
        holder.bind(character)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Character?>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }


    }

    class CharacterViewHolder(private var binding: GridViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character?) {
            binding.character = CharacterViewModel(character)
            binding.executePendingBindings()
        }
    }

    class CharacterViewModel(item: Character?) : ViewModel() {
        val name = ObservableField(item?.name)
    }

    class OnClickListener(val clickListener: (character: Character?) -> Unit) {
        fun onClick(character: Character?) = clickListener(character)
    }
}
