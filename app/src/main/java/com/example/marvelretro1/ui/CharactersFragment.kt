package com.example.marvelretro1.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.marvelretro1.LoginActivity
import com.example.marvelretro1.MainActivity
import com.example.marvelretro1.R
import com.example.marvelretro1.databinding.FragmentCharactersBinding
import com.example.marvelretro1.modeladoClase.Result
import com.example.marvelretro1.utils.AlertUtils
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersFragment : Fragment() {

    private val handler = Handler()

    private var _binding: FragmentCharactersBinding? = null
    private val binding: FragmentCharactersBinding
        get() = _binding!!

    private val viewModel by viewModel<CharactersViewModel>()

    private var listener: OnFragmentInteractionListener? = null
    private var logOutListener: LogOutInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharactersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageButton.setOnClickListener{
            logOutListener?.signOutAndStartSignInActivity()
        }

        setupSearchView()
        setupList()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.isVisible = isLoading
        }

        viewModel.characters.observe(viewLifecycleOwner) { characters ->
            submitToList(characters)
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            AlertUtils.showErrorAlert(requireContext(), message = errorMessage)
        }
    }

    private fun submitToList(characters: List<Result>) {
        binding.emptyState.isVisible = characters.isEmpty()
        (binding.armorsRecyclerView.adapter as CharactersAdapter).submitList(characters)
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                handler.removeCallbacksAndMessages(null)

                if(newText.isNullOrEmpty()) {
                    submitToList(viewModel.filterCharacters(newText ?: ""))
                } else {
                    handler.postDelayed({
                        submitToList(viewModel.filterCharacters(newText))
                    }, 1000)
                }
                return false
            }
        })
    }

    private fun setupList() {
        binding.armorsRecyclerView.adapter = CharactersAdapter { character ->
            listener?.showArmorDetails(character)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw NotImplementedError("$context Must implement interface ArmorsFragment.OnFragmentInteractionListener")
        }
        if(context is LogOutInteractionListener) {
            logOutListener = context
        } else {
            throw NotImplementedError("$context Must implement interface ArmorsFragment.OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

    interface OnFragmentInteractionListener {
        fun showArmorDetails(character: Result)
    }

    interface LogOutInteractionListener {
        fun signOutAndStartSignInActivity()
    }

}