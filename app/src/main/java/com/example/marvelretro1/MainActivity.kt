package com.example.marvelretro1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.example.marvelretro1.constants.MarvelConstants
import com.example.marvelretro1.modeladoClase.Result
import com.example.marvelretro1.databinding.ActivityMainBinding
import com.example.marvelretro1.ui.CharactersFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity(), CharactersFragment.OnFragmentInteractionListener, CharactersFragment.LogOutInteractionListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* --- Login verification --- */
        mAuth = FirebaseAuth.getInstance()

        //default_web_client_id = marvelretro1
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        val auth = Firebase.auth
        val user = auth.currentUser

        if (user == null) {
            signOutAndStartSignInActivity()
        }

        /* --- Login verification --- */

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                add(binding.fragmentContainer.id, CharactersFragment())
                commit()
            }
        } else {
            supportFragmentManager.fragments.last()
        }
    }

    override fun showArmorDetails(character: Result) {
        val intent = Intent(applicationContext, CharacterDetailsActivity::class.java)
        val bundle = Bundle()
        bundle.putString(MarvelConstants.BUNDLE.DESCRIPTION, character.description)
        bundle.putInt(MarvelConstants.BUNDLE.ID, character.id)
        bundle.putString(MarvelConstants.BUNDLE.NAME, character.name)
        bundle.putString(
            MarvelConstants.BUNDLE.THUMBNAIL,
            character.thumbnail.path + "." + character.thumbnail.extension
        )
        intent.putExtras(bundle)

        startActivity(intent)
    }

    override fun signOutAndStartSignInActivity() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Logout Alert")
        builder.setMessage("Do you want to close session?")

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            Toast.makeText(applicationContext,
                android.R.string.yes, Toast.LENGTH_SHORT).show()
            mAuth.signOut()

            mGoogleSignInClient.signOut().addOnCompleteListener(this) {
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            Toast.makeText(applicationContext,
                android.R.string.no, Toast.LENGTH_SHORT).show()
        }

        builder.show()




    }

}