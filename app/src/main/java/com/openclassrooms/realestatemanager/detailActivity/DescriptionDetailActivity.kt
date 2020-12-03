package com.openclassrooms.realestatemanager.detailActivity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.openclassrooms.realestatemanager.R
import com.openclassrooms.realestatemanager.databinding.ActivityDescriptionDetailBinding

class DescriptionDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDescriptionDetailBinding
    private var description: String? = ""
    private var photoCover: String? = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_description_detail)

        loadPref()
    }

    private fun loadPref() {
        val sharedPreferences = getSharedPreferences(DetailActivity.PREF_DESC, Context.MODE_PRIVATE)
        description = sharedPreferences.getString("description", "")
        binding.tvDescriptionDetail.text = description
        photoCover = sharedPreferences.getString("photoCover", "")
        binding.ivPhotoCover.setImageResource(photoCover.toString().toInt())


    }
}
