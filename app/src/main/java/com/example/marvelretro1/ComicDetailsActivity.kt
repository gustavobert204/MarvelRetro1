package com.example.marvelretro1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.marvelretro1.constants.MarvelConstants
import com.example.marvelretro1.databinding.ActivityComicDetailBinding
import com.example.marvelretro1.ui.CharacterComicsAdapter
import com.example.marvelretro1.ui.ComicCharactersAdapter
import com.example.marvelretro1.ui.ComicCharactersViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ComicDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityComicDetailBinding

    private val viewModel by viewModel<ComicCharactersViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComicDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.comicDetailBack.setOnClickListener {
            onBackPressed()
        }

        val bundle = intent.extras
        if (bundle != null) {
            binding.comicDetailTitle.text = bundle.getString(MarvelConstants.BUNDLE_COMICS.TITLE).toString()
            val description = bundle.getString(MarvelConstants.BUNDLE_COMICS.DESCRIPTION).toString()
            binding.comicDetailDescription.text = if (description.isNotEmpty()) description else "No description provided"

            Glide.with(binding.root.context)
                .load(bundle.getString(MarvelConstants.BUNDLE_COMICS.THUMBNAIL))
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .fallback(R.mipmap.ic_launcher)
                .into(binding.comicDetailPoster)

            viewModel.getCharacters(bundle.getInt(MarvelConstants.BUNDLE_COMICS.ID))
            binding.comicDetailCharactersRV.layoutManager =  LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            binding.comicDetailCharactersRV.adapter = ComicCharactersAdapter() {}

            viewModel.charactersComics.observe(this){
                (binding.comicDetailCharactersRV.adapter as ComicCharactersAdapter).submitList(it)
            }
        }

    }
}