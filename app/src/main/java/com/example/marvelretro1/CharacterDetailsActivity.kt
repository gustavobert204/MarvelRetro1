package com.example.marvelretro1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.marvelretro1.constants.MarvelConstants
import com.example.marvelretro1.data.responses.GetCharactersFailure
import com.example.marvelretro1.data.responses.GetCharactersSuccess
import com.example.marvelretro1.databinding.ActivityCharacterDetailsBinding
import com.example.marvelretro1.ui.CharacterComicsAdapter
import com.example.marvelretro1.ui.CharacterComicsViewModel
import com.example.marvelretro1.ui.CharactersViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class CharacterDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterDetailsBinding

    private val viewModel by viewModel<CharacterComicsViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        if (bundle != null) {
            binding.characterDetailName.text = bundle.getString(MarvelConstants.BUNDLE.NAME).toString()
            val description = bundle.getString(MarvelConstants.BUNDLE.DESCRIPTION).toString()
            binding.characterDetailDescription.text = if (description.isNotEmpty()) description else "No description provided"

            Glide.with(this)
                .load(bundle.getString(MarvelConstants.BUNDLE.THUMBNAIL).toString())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .fallback(R.mipmap.ic_launcher)
                .into(binding.characterDetailImage)

            viewModel.getCharacters(bundle.getInt(MarvelConstants.BUNDLE.ID))
            binding.characterDetailComicsRv.layoutManager =  LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            binding.characterDetailComicsRv.adapter = CharacterComicsAdapter() {}

            viewModel.charactersComics.observe(this){
                (binding.characterDetailComicsRv.adapter as CharacterComicsAdapter).submitList(it)
            }
        }
    }
}