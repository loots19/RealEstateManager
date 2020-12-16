package com.openclassrooms.realestatemanager.detailActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.openclassrooms.realestatemanager.R
import com.openclassrooms.realestatemanager.databinding.ActivityFullScreenBinding

class FullScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFullScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_full_screen)
        actionOnBtnBack()

        val intent = intent
        val dImage = intent.getIntExtra("iImage", 0)

        Glide.with(this)
                .load(dImage.toString().toInt())
                .fitCenter()
                .into(binding.ivFullScreenActivity)

    }

    fun actionOnBtnBack() {
        binding.imageButtonBack.setOnClickListener {
            finish()
        }
    }


}
