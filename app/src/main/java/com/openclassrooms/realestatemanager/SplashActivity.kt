package com.openclassrooms.realestatemanager

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.openclassrooms.realestatemanager.auth.AuthActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //hiding tittle bar of this activity
        window.requestFeature(Window.FEATURE_NO_TITLE)
        //making this activity full screen
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)

        //3second splash time
        Handler().postDelayed({

            // start main activity
            userLogged()
            // finish this activity
            finish()
        }, 2000)


    }

    private fun userLogged() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            launchMainActivity()
            Log.e("test", "yes")

        } else {
            launchSignIn()

            Log.e("test", "no")
        }
    }

    // -------------------------------------------------------
    // ----------------- Launch main activity -----------------
    // -------------------------------------------------------
    private fun launchMainActivity() {
        startActivity((Intent(this@SplashActivity, MainActivity::class.java)))
        finish()
    }

    private fun launchSignIn() {
        startActivity(Intent(this@SplashActivity, AuthActivity::class.java))
        finish()
    }
}
