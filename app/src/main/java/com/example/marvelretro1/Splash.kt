package com.example.marvelretro1


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelretro1.databinding.ActivityComicDetailBinding
import com.example.marvelretro1.databinding.ActivitySplashBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class Splash : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var anim = AnimationUtils.loadAnimation(applicationContext, R.anim.fade_in)

        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {

                /* --- Login verification --- */
                mAuth = FirebaseAuth.getInstance()

                //default_web_client_id = marvelretro1
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build()

                mGoogleSignInClient = GoogleSignIn.getClient(binding.root.context, gso)

                val auth = Firebase.auth
                val user = auth.currentUser

                if (user == null) {
                    startActivity(Intent(binding.root.context, LoginActivity::class.java))
                    finish()
                } else {
                    startActivity(Intent(binding.root.context, MainActivity::class.java))
                    finish()
                }

                /* --- Login verification --- */
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
        binding.imageView2.startAnimation(anim)
    }
}