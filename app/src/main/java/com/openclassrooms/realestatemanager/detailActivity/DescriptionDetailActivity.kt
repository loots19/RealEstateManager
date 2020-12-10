package com.openclassrooms.realestatemanager.detailActivity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.openclassrooms.realestatemanager.R
import com.openclassrooms.realestatemanager.databinding.ActivityDescriptionDetailBinding
import kotlinx.android.synthetic.main.activity_description_detail.*

class DescriptionDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDescriptionDetailBinding
    private lateinit var description: String
    private lateinit var photoCover: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_description_detail)
        loadPref()
    }

    private fun loadPref() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            intent.action = Intent.ACTION_GET_CONTENT
        } else {
            intent.action = Intent.ACTION_OPEN_DOCUMENT
            intent.addCategory(Intent.CATEGORY_OPENABLE)

            val sharedPreferences = getSharedPreferences(DetailActivity.PREF_DESC, Context.MODE_PRIVATE)
            description = sharedPreferences.getString("description", "")
            binding.tvDescriptionDetail.text = description
            photoCover = sharedPreferences.getString("photoCover", "")


            if (photoCover.contains("images")) {
                Glide.with(this)
                        .load(photoCover)
                        .centerCrop()
                        .into(iv_photo_cover)

            } else {
                // this because drawable in my fakePropertyApi
                val photo = resources.getIdentifier(photoCover, "drawable", "")
                Glide.with(this)
                        .load(photo)
                        .centerCrop()
                        .into(iv_photo_cover)
            }

        }


    }

}



