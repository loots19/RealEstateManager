package com.openclassrooms.realestatemanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.google.android.material.bottomnavigation.BottomNavigationView

class FullScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen)

        val ivFullScreen = findViewById<ImageView>(R.id.ivFullScreenActivity)


        var intent = intent
        val dImage = intent.getIntExtra("iImage",0)
        ivFullScreen.setImageResource(dImage)

    }




}
