package com.openclassrooms.realestatemanager

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class DetailActivity : AppCompatActivity() {

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_detail)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        // adding inbuilt divider line
        // recyclerView!!.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL))


        val images = ArrayList<Image>()

        images.add(Image(R.drawable.dollar,"dollars"))
        images.add(Image(R.drawable.logo,"logo"))
        images.add(Image(R.drawable.dollar,"dollars"))
        images.add(Image(R.drawable.logo,"logo"))
        images.add(Image(R.drawable.dollar,"dollars"))
        images.add(Image(R.drawable.dollar,"dollars"))
        images.add(Image(R.drawable.agency,"dollars"))
        images.add(Image(R.drawable.dollar,"dollars"))
        images.add(Image(R.drawable.logo,"logo"))
        images.add(Image(R.drawable.dollar,"dollars"))
        images.add(Image(R.drawable.agency,"dollars"))


        val adapter = DetailAdapter(images,this){ item->
            startActivity(Intent(this@DetailActivity, FullScreenActivity:: class.java))

        }


        recyclerView.adapter = adapter
    }
}
