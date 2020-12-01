package com.openclassrooms.realestatemanager.detailActivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.openclassrooms.realestatemanager.ConversionActivity
import com.openclassrooms.realestatemanager.R
import com.openclassrooms.realestatemanager.databinding.ActivityDetailBinding
import com.openclassrooms.realestatemanager.model.Photo
import com.openclassrooms.realestatemanager.model.Property
import kotlinx.android.synthetic.main.activity_main.*


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        val EXTRA_PROPERTY = "property"
    }


    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, com.openclassrooms.realestatemanager.R.layout.activity_detail)
        configureToolbar()
        getIncomingIntent()

        binding.tvDescDetail.movementMethod = ScrollingMovementMethod()
        binding.imageViewConvert.setOnClickListener {
            launchConvertActivity()
        }

        binding.rvDetail.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)

        val images = ArrayList<Photo>()

        images.add(Photo(0, com.openclassrooms.realestatemanager.R.drawable.agency.toString(), "dollars", 0))
        images.add(Photo(0, com.openclassrooms.realestatemanager.R.drawable.dollar.toString(), "dollars", 0))
        images.add(Photo(0, com.openclassrooms.realestatemanager.R.drawable.dollar.toString(), "dollars", 0))
        images.add(Photo(0, com.openclassrooms.realestatemanager.R.drawable.dollar.toString(), "dollars", 0))


        val adapter = DetailAdapter(images) { item ->
            val intent = Intent(this, FullScreenActivity::class.java)
            intent.putExtra("iImage", item.urlPhoto.toInt())
            this.startActivity(intent)
        }

        binding.rvDetail.adapter = adapter
    }


    private fun launchConvertActivity() {
        startActivity(Intent(this@DetailActivity, ConversionActivity::class.java))

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_edit, menu)
        return true
    }

    // initialize toolBar
    private fun configureToolbar() {
        setSupportActionBar(toolbar as Toolbar?)
    }
    //click event on item in toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.edit -> {

            }


        }
        return super.onOptionsItemSelected(item)
    }

    private fun getIncomingIntent() {
        if (intent.hasExtra(EXTRA_PROPERTY)) {
            val jsonResult = intent.getStringExtra(EXTRA_PROPERTY)
            val property = Gson().fromJson(jsonResult, Property::class.java)
            binding.etPriceDetail.setText(property.price.toString() + " " + getString(R.string.dollars))
            binding.tvDescDetail.text = property.description
            binding.etNbrRoomDetail.setText( property.nbrRoom.toString())
            binding.tvLocationDetail.text = property.address
            binding.etSurfaceDetail.setText( property.surface.toString())
            binding.etNbrRoomDetail.setText(property.nbrRoom.toString())
            binding.etNbrBathDetail.setText(property.nbrBathRoom.toString())
            binding.etNbrBedDetail.setText (property.nbrBedRoom.toString())
            binding.tvDateDetail.text = property.dateEntry


        }


    }
}