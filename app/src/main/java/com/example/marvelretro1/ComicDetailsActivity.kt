package com.example.marvelretro1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.marvelretro1.constants.MarvelConstants
import com.example.marvelretro1.databinding.ActivityComicDetailBinding

class ComicDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityComicDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComicDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        if (bundle != null) {
            binding.comicDetailTitle.text = bundle.getString(MarvelConstants.BUNDLE_COMICS.TITLE).toString()
            val description = bundle.getString(MarvelConstants.BUNDLE_COMICS.DESCRIPTION).toString()
            binding.comicDetailDescription.text = if (description.isNotEmpty()) description else "No description provided"

            Glide.with(binding.root.context)
                .load(bundle.getString(MarvelConstants.BUNDLE_COMICS.THUMBNAIL))
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .fallback(R.mipmap.ic_launcher)
                .into(binding.comicDetailPoster)
        }

    }
}