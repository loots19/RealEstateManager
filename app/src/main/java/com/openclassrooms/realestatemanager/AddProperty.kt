package com.openclassrooms.realestatemanager

import android.Manifest
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.chip.Chip
import com.openclassrooms.realestatemanager.detailActivity.DetailAdapter
import com.openclassrooms.realestatemanager.detailActivity.FullScreenActivity
import com.openclassrooms.realestatemanager.detailActivity.Image
import java.io.File
import java.io.IOException
import java.util.*

class AddProperty : AppCompatActivity() {
    @BindView(R.id.spinner_type_create)
    lateinit var spType: Spinner
    @BindView(R.id.chip_market_create)
    lateinit var chMarket: Chip
    @BindView(R.id.CheckBox)
    lateinit var cBox: CheckBox
    @BindView(R.id.IViewCalendar)
    lateinit var ivCalendar: ImageView
    @BindView(R.id.tv_date_create)
    lateinit var tvDate: TextView
    @BindView(R.id.tvPhoto)
    lateinit var tvPhoto: TextView
    @BindView(R.id.tv_photo_folder)
    lateinit var tvGallery: TextView
    @BindView(R.id.rv_photo_create)
    lateinit var rvPhoto: RecyclerView
    @BindView(R.id.tv_agent_create)
    lateinit var etName: TextView


    private var c = Calendar.getInstance()
    private var year = c.get(Calendar.YEAR)
    private var month = c.get(Calendar.MONTH) + 1
    private var day = c.get(Calendar.DAY_OF_MONTH)

    private val REQUEST_PERMISSION = 100
    private val REQUEST_IMAGE_CAPTURE = 1
    private val REQUEST_PICK_IMAGE = 2
    lateinit var currentPhotoPath: String
    private val images = ArrayList<Image>()



    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_property)
        ButterKnife.bind(this)

        actionOnCBox()
        selectDateOfEntry()
        checkPermission()
        takePhoto()
        photoGallery()
        getName()


        rvPhoto.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)

        val adapter = DetailAdapter(images, this) { item ->
            val intent = Intent(this, FullScreenActivity::class.java)
            intent.putExtra("iImage", item.image)
            this.startActivity(intent)

        }
        rvPhoto.adapter = adapter

        val adapterSpinner: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(this, R.array.type, android.R.layout.simple_spinner_item)
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spType.adapter = adapterSpinner
    }


    // Action of User

    private fun actionOnCBox() {
        ivCalendar.visibility = View.GONE
        cBox.setOnClickListener {
            if (cBox.isChecked) {
                ivCalendar.visibility = View.VISIBLE
            } else {
                ivCalendar.visibility = View.GONE
            }
        }
        selectDateOfSale()
    }

    private fun selectDateOfSale() {
        ivCalendar.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                Log.e("Date", "$dayOfMonth/$monthOfYear/$year")
            }, year, month, day)
            dpd.show()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun selectDateOfEntry() {
        tvDate.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                tvDate.text = "$dayOfMonth/$monthOfYear/$year"
            }, year, month, day)
            dpd.show()
        }
    }

    private fun takePhoto() {
        tvPhoto.setOnClickListener {
            openCamera()
        }
    }

    private fun photoGallery() {
        tvGallery.setOnClickListener {
            openGallery()
        }
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.CAMERA),
                    REQUEST_PERMISSION)
        }
    }

    private fun openGallery() {
        Intent(Intent.ACTION_GET_CONTENT).also { intent ->
            intent.type = "image/*"
            intent.resolveActivity(packageManager)?.also {
                startActivityForResult(intent, REQUEST_PICK_IMAGE)
            }
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
                val uri = (currentPhotoPath)
                // ivTest.setImageURI(uri)
                galleryAddPic()

                images.add(Image(uri, "test"))

                val adapter = DetailAdapter(images, this) { item ->

                }
                rvPhoto.adapter = adapter
                adapter.notifyDataSetChanged()


            } else if (requestCode == REQUEST_PICK_IMAGE) {
                val uri = data?.data
                images.add(Image(uri.toString(), "test"))

                val adapter = DetailAdapter(images, this) { item ->

                }
                rvPhoto.adapter = adapter
                adapter.notifyDataSetChanged()

            }
        }
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
            Log.e("photo",currentPhotoPath)
            mediaScanIntent.data = Uri.fromFile(f)
            sendBroadcast(mediaScanIntent)
        }
    }

    private fun getName() {
        val shared = getSharedPreferences("connection", Context.MODE_PRIVATE)
        shared.apply {
            val name = getString("pref_name", "")
            etName.text = name
        }

    }


}

















