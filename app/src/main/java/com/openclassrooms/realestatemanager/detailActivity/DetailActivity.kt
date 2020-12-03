package com.openclassrooms.realestatemanager.detailActivity

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
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
import com.openclassrooms.realestatemanager.viewModels.AgentViewModel
import com.openclassrooms.realestatemanager.viewModels.PropertyViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.collections.ArrayList


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var type: String = ""
    private var city: String = ""
    private var surface: Int = 0
    private var room: Int = 0
    private var bedRoom: Int = 0
    private var bathRoom: Int = 0
    private var price: String = ""
    private var propertyLat: Double = 0.0
    private var propertyLng: Double = 0.0
    private var propertyId: Long = 0
    private var agentId: Int = 0
    private var address: String = ""
    private var date: String = ""
    private var dateSale: String = ""
    private var agentName: String = ""
    private var agentNameEqual: String = ""
    private var description: String = ""
    private var photoCover: String = ""
    private var c = Calendar.getInstance()
    private var year = c.get(Calendar.YEAR)
    private var month = c.get(Calendar.MONTH)
    private var day = c.get(Calendar.DAY_OF_MONTH)


    companion object {
        const val EXTRA_PROPERTY = "property"
        const val PREF_PRICE = "price"
        const val PREF_DESC = "description"
    }

    private val mPropertyViewModel by viewModel<PropertyViewModel>()
    private val mAgentViewModel by viewModel<AgentViewModel>()

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        configureToolbar()
        getIncomingIntent()
        launchActivities()
        actionOnCBox()
        actionOnFab()

        binding.cardView4.visibility = View.GONE
        binding.buttonUpdate.visibility = View.GONE

        binding.rvDetail.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)

    }

    //--------------------------
    //----- LaunchActivities -----
    //--------------------------
    private fun launchActivities() {
        launchDetailDesc()
        launchFullScreenActivity()

        binding.imageViewConvert.setOnClickListener {
            launchConvertActivity()
        }
    }

    private fun launchConvertActivity() {
        price = binding.etPriceDetail.text.toString()
        val sharedPreferences = getSharedPreferences(PREF_PRICE, Context.MODE_PRIVATE).edit()
        sharedPreferences.putString("price", price)
                .apply()
        startActivity(Intent(this@DetailActivity, ConversionActivity::class.java))
    }

    private fun launchDetailDesc() {
        binding.tvDescDetail.setOnClickListener {
            description = binding.tvDescDetail.text.toString()

            val sharedPreferences = getSharedPreferences(PREF_DESC, Context.MODE_PRIVATE).edit()
            sharedPreferences.putString("description", description)
            sharedPreferences.putString("photoCover", photoCover)
                    .apply()
            startActivity(Intent(this@DetailActivity, DescriptionDetailActivity::class.java))
        }
    }

    private fun launchFullScreenActivity() {
        val images = ArrayList<Photo>()

        images.add(Photo(0, R.drawable.agency, "dollars", 0))
        images.add(Photo(0, R.drawable.dollar, "dollars", 0))
        images.add(Photo(0, R.drawable.dollar, "dollars", 0))
        images.add(Photo(0, R.drawable.dollar, "dollars", 0))

        val adapter = DetailAdapter(images) { item ->
            val intent = Intent(this, FullScreenActivity::class.java)
            intent.putExtra("iImage", item.urlPhoto.toInt())
            this.startActivity(intent)
        }

        binding.rvDetail.adapter = adapter
    }

    //----------------------------
    // ----- Initialize View -----
    //----------------------------
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_edit, menu)
        return true
    }

    // initialize toolBar
    private fun configureToolbar() {
        setSupportActionBar(toolbar as Toolbar?)
    }

    // --------------------
    // ----- EditInfo -----
    // --------------------

    //click event on item in toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.edit -> {
                checkAgentCanEdit()
                binding.IViewCalendar.visibility = View.GONE
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun actionOnCBox() {
        binding.IViewCalendar.visibility = View.GONE
        binding.CheckBox.setOnClickListener {
            if (binding.CheckBox.isChecked) {
                binding.IViewCalendar.visibility = View.VISIBLE
            } else {
                binding.IViewCalendar.visibility = View.GONE
            }
        }
        selectDateOfSale()
    }

    @SuppressLint("SetTextI18n")
    private fun selectDateOfSale() {
        binding.IViewCalendar.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                binding.tvDateSoldOutDetail.text = "$dayOfMonth/$monthOfYear/$year"
                dateSale = "$dayOfMonth/$monthOfYear/$year"

            }, year, month, day)
            dpd.show()
        }
    }

    private fun actionOnFab() {
        binding.buttonUpdate.setOnClickListener {
            mPropertyViewModel.getProperty(propertyId).observe(this, androidx.lifecycle.Observer {
                updateData()
                val property = Property(propertyId, city, price, type, surface, room, bathRoom, bedRoom, address, date, photoCover, description,
                        dateSale, propertyLat, propertyLng, agentId, agentName)

                mPropertyViewModel.updateProperty(property)
            })
            finish()
        }
    }

    private fun updateData() {
        price = binding.etPriceDetail.text.toString()
        surface = binding.etSurfaceDetail.text.toString().toInt()
        room = binding.etNbrRoomDetail.text.toString().toInt()
        bathRoom = binding.etNbrBathDetail.text.toString().toInt()
        bedRoom = binding.etNbrBedDetail.text.toString().toInt()
        address = binding.tvLocationDetail.text.toString()
        date = binding.tvDateDetail.text.toString()
        description = binding.tvDescDetail.text.toString()
        dateSale = binding.tvDateSoldOutDetail.text.toString()
        agentName = binding.etManageDetail.text.toString()
        type = binding.tvTypeDetail.text.toString()
        city = binding.tvCityDetail.text.toString()

    }

    // get the name of agent for check it's possible to edit property
    private fun checkAgentCanEdit() {
        mAgentViewModel.getAgent().observe(this, androidx.lifecycle.Observer {
            if (it != null) {
                agentNameEqual = it.name
                agentName = binding.etManageDetail.text.toString()
                if (agentNameEqual == agentName) {
                    binding.cardView4.visibility = View.VISIBLE
                    binding.buttonUpdate.visibility = View.VISIBLE

                } else {
                    Toast.makeText(this, "you can just edit your properties", Toast.LENGTH_LONG).show()
                }
            }
        })
    }


    //----------------------
    // ----- GetIntent -----
    //----------------------
    private fun getIncomingIntent() {
        if (intent.hasExtra(EXTRA_PROPERTY)) {
            val jsonResult = intent.getStringExtra(EXTRA_PROPERTY)
            val property = Gson().fromJson(jsonResult, Property::class.java)
            binding.etPriceDetail.setText(property.price)
            binding.tvDescDetail.text = property.description
            binding.etNbrRoomDetail.setText(property.nbrRoom.toString())
            binding.tvLocationDetail.text = property.address
            binding.etSurfaceDetail.setText(property.surface.toString())
            binding.etNbrRoomDetail.setText(property.nbrRoom.toString())
            binding.etNbrBathDetail.setText(property.nbrBathRoom.toString())
            binding.etNbrBedDetail.setText(property.nbrBedRoom.toString())
            binding.tvDateDetail.text = property.dateEntry
            binding.etManageDetail.setText(property.agentName)
            binding.tvTypeDetail.text = property.type
            binding.tvCityDetail.text = property.city
            photoCover = property.photoCover
            propertyId = property.id


        }
    }
}

