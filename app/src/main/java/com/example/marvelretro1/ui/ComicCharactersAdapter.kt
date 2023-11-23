package com.example.marvelretro1.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelretro1.CharacterDetailsActivity
import com.example.marvelretro1.ComicDetailsActivity
import com.example.marvelretro1.R
import com.example.marvelretro1.constants.MarvelConstants
import com.example.marvelretro1.data.models.comicCharacters.Result
import com.example.marvelretro1.databinding.ComicCharactersListItemBinding

class ComicCharactersAdapter(private val onItemClickListener: (Result) -> Unit) : ListAdapter<Result, ComicCharactersAdapter.ViewHolder>(ArmorsDiffUtilCallback()) {

    class ViewHolder(private val binding: ComicCharactersListItemBinding, private val onItemClickListener: (Result) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Result) {
            binding.comicCharacterName.text = item.name

            val thumbnailUrl = item.thumbnail.path + "." + item.thumbnail.extension
            Glide.with(binding.root.context)
                .load(thumbnailUrl)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .fallback(R.mipmap.ic_launcher)
                .into(binding.comicCharacterPoster)


            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, CharacterDetailsActivity::class.java)
                val bundle = Bundle()
                bundle.putString(MarvelConstants.BUNDLE.DESCRIPTION, item.description)
                bundle.putInt(MarvelConstants.BUNDLE.ID, item.id)
                bundle.putString(MarvelConstants.BUNDLE.NAME, item.name)
                bundle.putString(
                    MarvelConstants.BUNDLE.THUMBNAIL,
                    item.thumbnail.path + "." + item.thumbnail.extension
                )
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
            ComicCharactersListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemClickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}