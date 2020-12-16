package com.openclassrooms.realestatemanager.detailActivity

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import com.openclassrooms.realestatemanager.ConversionActivity
import com.openclassrooms.realestatemanager.R
import com.openclassrooms.realestatemanager.databinding.ActivityDetailBinding
import com.openclassrooms.realestatemanager.model.Photo
import com.openclassrooms.realestatemanager.model.Property
import com.openclassrooms.realestatemanager.utils.Utils
import com.openclassrooms.realestatemanager.utils.UtilsKotlin
import com.openclassrooms.realestatemanager.viewModels.AgentViewModel
import com.openclassrooms.realestatemanager.viewModels.PhotoViewModel
import com.openclassrooms.realestatemanager.viewModels.PropertyViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.collections.ArrayList


class DetailActivity : AppCompatActivity() {
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
    private val photo = ArrayList<Photo>()

    private lateinit var binding: ActivityDetailBinding
    private lateinit var photos: MutableList<Photo>
    private lateinit var addressTxt: String
    private lateinit var latlng: LatLng
    private lateinit var resultLatLng: String


    companion object {
        const val EXTRA_PROPERTY = "property"
        const val PREF_PRICE = "price"
        const val PREF_DESC = "description"
    }

    private val mPropertyViewModel by viewModel<PropertyViewModel>()
    private val mAgentViewModel by viewModel<AgentViewModel>()
    private val mPhotoViewModel by viewModel<PhotoViewModel>()

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        // this for plain layout
        window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        initializationRv()
        unableViewVisibility()
        actionOfUser()
        getIncomingIntent()
        showStaticMaps()
        checkAgentCanEdit()
        setImageCover()
        launchConvertActivity()


        photos = mutableListOf()


    }

    private fun actionOfUser() {
        actionOnBtnEdit()
        actionOnBackBtn()
        actionOnFab()
        actionOnCBox()
    }

    @SuppressLint("WrongConstant")
    private fun initializationRv() {
        binding.rvDetail.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        binding.rvDetail.adapter = adapter

    }

    private val adapter = DetailAdapter(photo) { item ->
        val intent = Intent(this, FullScreenActivity::class.java)
        intent.putExtra("iImage", item.urlPhoto.toInt())
        this.startActivity(intent)

    }

    private fun launchConvertActivity() {
        binding.imageViewConvert.setOnClickListener {
            price = binding.etPriceDetail.text.toString()
            val sharedPreferences = getSharedPreferences(PREF_PRICE, Context.MODE_PRIVATE).edit()
            sharedPreferences.putString("price", price)
                    .apply()
            startActivity(Intent(this@DetailActivity, ConversionActivity::class.java))
        }
    }

    private fun setImageCover() {
        if (photoCover.contains("images")) {
            Glide.with(this)
                    .load(photoCover)
                    .centerCrop()
                    .into(iv_plain_Detail)

        } else {
            // this because drawable in my fakePropertyApi
            val photo = resources.getIdentifier(photoCover, "drawable", "")
            Glide.with(this)
                    .load(photo)
                    .centerCrop()
                    .into(iv_plain_Detail)
        }

    }

    // --------------------
    // ----- EditInfo -----
    // --------------------

    //click event on edit button
    fun actionOnBtnEdit() {
        binding.imageButtonEdit.setOnClickListener {
            binding.cardView4.visibility = View.VISIBLE
            binding.buttonUpdate.visibility = View.VISIBLE
            binding.IViewCalendar.visibility = View.GONE
        }
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
                binding.tvDateSoldOutDetail.text = "Sold out $dayOfMonth/$monthOfYear/$year"
                dateSale = "$dayOfMonth/$monthOfYear/$year"

            }, year, month, day)
            dpd.show()
        }
    }

    private fun unableViewVisibility() {
        binding.cardView4.visibility = View.GONE
        binding.buttonUpdate.visibility = View.GONE

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

    private fun actionOnBackBtn() {
        binding.imageButtonBack.setOnClickListener {
            finish()
        }
    }

    private fun updateData() {
        price = binding.etPriceDetail.text.toString()
        surface = binding.etSurfaceDetail.text.toString().toInt()
        room = binding.etNbrRoomDetail.text.toString().toInt()
        bathRoom = binding.etNbrBathDetail.text.toString().toInt()
        bedRoom = binding.etNbrBedDetail.text.toString().toInt()
        address = binding.etAddressDetail.text.toString()
        date = binding.etDateDetail.text.toString()
        description = binding.tvDescDetail.text.toString()
        dateSale = binding.tvDateSoldOutDetail.text.toString()
        agentName = binding.etManageDetail.text.toString()
        type = binding.etTypeDetail.text.toString()
        city = binding.etCityDetail.text.toString()

    }

    // --------------------------------------------------------------------------
    // ----- get the name of agent for check it's possible to edit property -----
    // --------------------------------------------------------------------------
    private fun checkAgentCanEdit() {
        mAgentViewModel.getAgent().observe(this, androidx.lifecycle.Observer {
            if (it != null) {
                agentNameEqual = it.name
                agentName = binding.etManageDetail.text.toString()
                if (agentNameEqual == agentName) {
                    binding.imageButtonEdit.visibility = View.VISIBLE
                } else {
                    binding.imageButtonEdit.visibility = View.GONE
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
            binding.etAddressDetail.setText(property.address)
            binding.etSurfaceDetail.setText(property.surface.toString())
            binding.etNbrRoomDetail.setText(property.nbrRoom.toString())
            binding.etNbrBathDetail.setText(property.nbrBathRoom.toString())
            binding.etNbrBedDetail.setText(property.nbrBedRoom.toString())
            binding.etDateDetail.setText(property.dateEntry)
            binding.etManageDetail.setText(property.agentName)
            binding.etTypeDetail.setText(property.type)
            binding.etCityDetail.setText(property.city)
            photoCover = property.photoCover
            propertyId = property.id

        }
    }

    private fun getPhoto(propertyId: Long) {
        mPhotoViewModel.getPhotoByPropertyId(propertyId).observe(this, androidx.lifecycle.Observer {
            if (it != null) {
                adapter.setProperties(it)

            }
        })
    }

    // ----------------------
    // ----- Static Map -----
    // ----------------------
    private fun showStaticMaps() {
        mPropertyViewModel.getProperty(propertyId).observe(this, androidx.lifecycle.Observer {
            if (UtilsKotlin.verifyAvailableNetwork(this) || (Utils.isInternetAvailable(this))) {
                //Toast.makeText(this, "connected to network", Toast.LENGTH_SHORT).show()
                addressTxt = "${it.address} ${it.city}"
                latlng = UtilsKotlin.getLocationFromAddress(this, addressTxt)!!
                resultLatLng = "${latlng.latitude},${latlng.longitude}"
                getPhoto(propertyId)
                val url = "https://maps.googleapis.com/maps/api/staticmap?center=$resultLatLng&markers=$resultLatLng&zoom=20&size=400x400&key=${UtilsKotlin.API_KEY}"
                Glide.with(this)
                        .load(url)
                        .into(binding.frameLayoutDetailActivity)
            } else {
                val imageNoConnection = R.drawable.ic_network_check_black_24dp
                Glide.with(this)
                        .load(imageNoConnection)
                        .into(binding.frameLayoutDetailActivity)
            }

        })

    }


}

