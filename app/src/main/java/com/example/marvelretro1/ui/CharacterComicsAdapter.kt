package com.example.marvelretro1.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelretro1.R
import com.example.marvelretro1.constants.MarvelConstants
import com.example.marvelretro1.databinding.CharacterComicsListItemBinding
import com.example.marvelretro1.data.models.charactercomics.Result

class CharacterComicsAdapter(private val onItemClickListener: (Result) -> Unit) : ListAdapter<Result, CharacterComicsAdapter.ViewHolder>(ArmorsDiffUtilCallback()) {

    class ViewHolder(private val binding: CharacterComicsListItemBinding, private val onItemClickListener: (Result) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Result) {
            binding.comicName.text = item.title

            Glide.with(binding.root.context)
                .load(item.thumbnail.path + "." + item.thumbnail.extension)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .fallback(R.mipmap.ic_launcher)
                .into(binding.comicPoster)

            Log.d("GUS", item.thumbnail.extension + "." + item.thumbnail.path)

/*
            binding.root.setOnClickListener {
                onItemClickListener(item)
            }*/
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
            CharacterComicsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemClickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}