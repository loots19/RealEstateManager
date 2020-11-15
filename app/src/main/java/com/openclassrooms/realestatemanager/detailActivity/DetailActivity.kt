package com.openclassrooms.realestatemanager.detailActivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.navigation.NavigationView
import com.openclassrooms.realestatemanager.R


class DetailActivity : AppCompatActivity(){

    @BindView(R.id.rv_detail)
    lateinit var recyclerView: RecyclerView

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        ButterKnife.bind(this)


        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        // adding inbuilt divider line
        // recyclerView!!.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL))


        val images = ArrayList<Image>()

        images.add(Image(R.drawable.dollar, "dollars"))
        images.add(Image(R.drawable.logo, "logo"))
        images.add(Image(R.drawable.dollar, "dollars"))
        images.add(Image(R.drawable.logo, "logo"))
        images.add(Image(R.drawable.dollar, "dollars"))
        images.add(Image(R.drawable.dollar, "dollars"))
        images.add(Image(R.drawable.agency, "dollars"))
        images.add(Image(R.drawable.dollar, "dollars"))
        images.add(Image(R.drawable.logo, "logo"))
        images.add(Image(R.drawable.dollar, "dollars"))
        images.add(Image(R.drawable.agency, "dollars"))


        val adapter = DetailAdapter(images, this) { item ->
            startActivity(Intent(this@DetailActivity, FullScreenActivity::class.java))

        }


        recyclerView.adapter = adapter
    }
   
}
