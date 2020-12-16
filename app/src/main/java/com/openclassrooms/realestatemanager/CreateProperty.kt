package com.openclassrooms.realestatemanager

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.openclassrooms.realestatemanager.databinding.ActivityAddPropertyBinding
import com.openclassrooms.realestatemanager.detailActivity.DetailAdapter
import com.openclassrooms.realestatemanager.koin.App
import com.openclassrooms.realestatemanager.model.Photo
import com.openclassrooms.realestatemanager.model.Property
import com.openclassrooms.realestatemanager.utils.UtilsKotlin
import com.openclassrooms.realestatemanager.viewModels.AgentViewModel
import com.openclassrooms.realestatemanager.viewModels.PhotoViewModel
import com.openclassrooms.realestatemanager.viewModels.PropertyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

class CreateProperty : AppCompatActivity() {

    private val mAgentViewModel by viewModel<AgentViewModel>()
    private val mPropertyViewModel by viewModel<PropertyViewModel>()
    private val mPhotoViewModel by viewModel<PhotoViewModel>()

    private lateinit var binding: ActivityAddPropertyBinding
    private var type: String = ""
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
    private var agentName: String = ""
    private var description: String = ""
    private var photoCover: String = ""
    private var city: String = ""
    private var uri: String = ""
    private var photoName = ""
    private var photoId: Long = 0
    private var photoUrl: String = ""

    private var c = Calendar.getInstance()
    private var year = c.get(Calendar.YEAR)
    private var month = c.get(Calendar.MONTH)
    var day = c.get(Calendar.DAY_OF_MONTH)

    companion object {
        const val REQUEST_PERMISSION = 100
        const val PERMISSION_CODE_READ = 10
        const val PERMISSION_CODE_WRITE = 20
    }

    private val REQUEST_IMAGE_CAPTURE = 1
    private val REQUEST_PICK_IMAGE = 2
    lateinit var notificationManager: NotificationManagerCompat
    lateinit var currentPhotoPath: String

    private val photo = ArrayList<Photo>()
    private var photos: MutableList<Photo> = mutableListOf()
    private val propertyList = ArrayList<Property>()


    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_property)


        UtilsKotlin.checkPermission(this)


        selectDateOfEntry()
        takePhoto()
        photoGallery()
        getName()
        actionOnFab()
        selectType()


        binding.rvPhotoCreate.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)


        //initialize notification manager
        notificationManager = NotificationManagerCompat.from(this)


    }

    private fun selectType() {
        val adapterSpinner: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(this, R.array.type, android.R.layout.simple_spinner_item)
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerTypeCreate.adapter = adapterSpinner

        binding.spinnerTypeCreate.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                type = parent!!.getItemAtPosition(position).toString()
            }
        }
    }


    @SuppressLint("SetTextI18n")
    private fun selectDateOfEntry() {
        binding.tvDateCreate.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView

                binding.tvDateCreate.text = "$dayOfMonth/$monthOfYear/$year"
            }, year, month, day)
            dpd.show()
        }
    }
    // ---------------
    // -----PHOTO-----
    // ---------------

    private fun takePhoto() {
        binding.tvPhoto.setOnClickListener {
            openCamera()
        }
    }

    private fun photoGallery() {
        binding.tvPhotoFolder.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                openGallery()
            }
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, REQUEST_PICK_IMAGE)
        }
    }

    private fun openCamera() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
            intent.resolveActivity(packageManager)?.also {
                val photoFile: File? = try {
                    createImageFile()

                } catch (ex: IOException) {
                    // If there is error while creating the File, it will be null
                    null
                }
                photoFile?.also {
                    val photoURI = FileProvider.getUriForFile(
                            this,
                            "${BuildConfig.APPLICATION_ID}.fileProvider",
                            it
                    )
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                uri = (currentPhotoPath)
                photoUrl = uri

                galleryAddPic()
                alertDialogPhoto()
                val adapter = DetailAdapter(photo) {
                }
                binding.rvPhotoCreate.adapter = adapter
                adapter.notifyDataSetChanged()

            } else if (requestCode == REQUEST_PICK_IMAGE) {
                uri = data?.data.toString()
                photoUrl = uri
                alertDialogPhoto()

                val adapter = DetailAdapter(photo) {
                }
                binding.rvPhotoCreate.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        }

        val adapter = DetailAdapter(photo) { item ->
            alertDAddPhoto()
            photoCover = item.urlPhoto

        }
        binding.rvPhotoCreate.adapter = adapter

        adapter.notifyDataSetChanged()

    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val fileName = "MyPicture"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("PHOTO_${fileName}", ".jpg", storageDir
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }

    private fun galleryAddPic() {
        Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).also { mediaScanIntent ->
            val f = File(currentPhotoPath)
            mediaScanIntent.data = Uri.fromFile(f)
            sendBroadcast(mediaScanIntent)
        }
    }

    // ------------------------------------
    // get the name of agent and display it
    // ------------------------------------
    private fun getName() {
        mAgentViewModel.getAgent().observe(this, androidx.lifecycle.Observer { it ->
            if (it != null) {
                agentName = it.name
                binding.tvAgentCreate.text = agentName
            }
        })
    }

    private fun actionOnFab() {
        binding.floatingActionButton.setOnClickListener {
            // createNotification()
            addProperty()
        }
    }

    // ------------------------
    // ----- Notification -----
    // ------------------------
    private fun createNotification() {
        val title: String = getString(R.string.app_name)
        val builder = NotificationCompat.Builder(this, App.channelId)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle(title)
                .setContentText("You have just created a property")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.BLUE)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .build()
        notificationManager.notify(1, builder)
    }

    private fun addProperty() {
        getAllInfoToCreate()
    }

    private fun getAllInfoToCreate() {
        if (binding.etSurfaceCreate.text.isEmpty() || binding.etRoomCreate.text.isEmpty() || binding.etBathroomCreate.text.isEmpty()
                || binding.etBedroomCreate.text.isEmpty() || binding.etAddressCreate.text.isEmpty() || binding.tvDateCreate.text.isEmpty()
                || binding.tvAgentCreate.text.isEmpty() || binding.etDescriptionCreate.text.isEmpty() || binding.etPriceCreate.text.isEmpty()
                || binding.etCityCreate.text.isEmpty()) {
            Toast.makeText(this, "you must complete all values", Toast.LENGTH_LONG).show()
        } else {
            surface = binding.etSurfaceCreate.text.toString().toInt()
            room = binding.etRoomCreate.text.toString().toInt()
            bathRoom = binding.etBathroomCreate.text.toString().toInt()
            bedRoom = binding.etBedroomCreate.text.toString().toInt()
            address = binding.etAddressCreate.text.toString()
            date = binding.tvDateCreate.text.toString()
            agentName = binding.tvAgentCreate.text.toString()
            description = binding.etDescriptionCreate.text.toString()
            price = binding.etPriceCreate.text.toString()
            agentName = binding.tvAgentCreate.text.toString()
            city = binding.etCityCreate.text.toString()

            createPropertyBdd()


        }
    }

    // create in bdd and save in fireBase
    private fun createPropertyBdd() {
        val property = Property(propertyId, city, price, type, surface, room, bathRoom, bedRoom, address, date, photoCover, description,
                "", propertyLat, propertyLng, agentId, agentName)

        mPropertyViewModel.createProperty(property, photos)
        mPhotoViewModel.addAllPhotos(photos)

        finish()
    }


    @SuppressLint("InflateParams")
    private fun alertDialogPhoto() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        builder.setTitle("Description of photo")
        val dialogLayout = inflater.inflate(R.layout.custom_alert_dialogue, null)
        val editText = dialogLayout.findViewById<EditText>(R.id.editText)
        builder.setView(dialogLayout)
        builder.setPositiveButton("OK") { _, _ ->
            photoName = editText.text.toString()
            if (photoName.isNotEmpty()) {
                photo.add(Photo(0, uri, photoName, 0))
                val photo = Photo(photoId, photoUrl, photoName, propertyId)
                photos.add(photo)
            } else {
                Toast.makeText(this, "you must add description", Toast.LENGTH_LONG).show()
            }
        }
        builder.show()
    }

    private fun alertDAddPhoto() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(" Add Photo Cover")
        builder.setPositiveButton(android.R.string.yes) { _, _ ->
            Toast.makeText(applicationContext,
                    android.R.string.yes, Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton(android.R.string.no) { _, _ ->
            Toast.makeText(applicationContext,
                    android.R.string.no, Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }
}

























