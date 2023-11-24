package com.example.marvelretro1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        // Inside onCreate() method
        /*val sign_out_button = findViewById<Button>(R.id.logout_button)
        sign_out_button.setOnClickListener {
            signOutAndStartSignInActivity()
        }*/

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
        mAuth.signOut()

        mGoogleSignInClient.signOut().addOnCompleteListener(this) {
            // Optional: Update UI or show a message to the user
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            //finish()
        }
    }

}