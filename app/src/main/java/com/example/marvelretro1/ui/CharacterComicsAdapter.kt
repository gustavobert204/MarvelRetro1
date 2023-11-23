package com.example.marvelretro1.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelretro1.ComicDetailsActivity
import com.example.marvelretro1.R
import com.example.marvelretro1.constants.MarvelConstants
import com.example.marvelretro1.databinding.CharacterComicsListItemBinding
import com.example.marvelretro1.data.models.charactercomics.Result

class CharacterComicsAdapter(private val onItemClickListener: (Result) -> Unit) : ListAdapter<Result, CharacterComicsAdapter.ViewHolder>(ArmorsDiffUtilCallback()) {

    class ViewHolder(private val binding: CharacterComicsListItemBinding, private val onItemClickListener: (Result) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Result) {
            binding.comicName.text = item.title

            val thumbnailUrl = item.thumbnail.path + "." + item.thumbnail.extension
            Glide.with(binding.root.context)
                .load(thumbnailUrl)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .fallback(R.mipmap.ic_launcher)
                .into(binding.comicPoster)


            binding.root.setOnClickListener {
                Log.d("gus", "click")

                val intent = Intent(binding.root.context, ComicDetailsActivity::class.java)
                val bundle = Bundle()
                //bundle.putString(MarvelConstants.BUNDLE.DESCRIPTION, character.description)
                bundle.putInt(MarvelConstants.BUNDLE_COMICS.ID, item.id)
                bundle.putString(MarvelConstants.BUNDLE_COMICS.TITLE, item.title)
                bundle.putString(MarvelConstants.BUNDLE_COMICS.THUMBNAIL, thumbnailUrl)
                bundle.putString(MarvelConstants.BUNDLE_COMICS.DESCRIPTION, item.description)
                intent.putExtras(bundle)

                ContextCompat.startActivity(binding.root.context, intent, null)

            }

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