package com.openclassrooms.realestatemanager.detailActivity

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import com.openclassrooms.realestatemanager.R

class FullScreenActivity : AppCompatActivity() {

    @BindView(R.id.ivFullScreenActivity)
    lateinit var ivFullScreen : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen)
        ButterKnife.bind(this)



        var intent = intent
        val dImage = intent.getIntExtra("iImage", 0)
        ivFullScreen.setImageResource(dImage)

    }




}
