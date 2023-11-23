package com.example.marvelretro1.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelretro1.databinding.CharactersListItemBinding
import com.example.marvelretro1.modeladoClase.Result

class CharactersAdapter(private val onItemClickListener: (Result) -> Unit) : ListAdapter<Result, CharactersAdapter.ViewHolder>(ArmorsDiffUtilCallback()) {

    class ViewHolder(private val binding: CharactersListItemBinding, private val onItemClickListener: (Result) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Result) {
            binding.name.text = item.name

            binding.root.setOnClickListener {
                onItemClickListener(item)
            }
        }
    }

    class ArmorsDiffUtilCallback : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CharactersListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemClickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}