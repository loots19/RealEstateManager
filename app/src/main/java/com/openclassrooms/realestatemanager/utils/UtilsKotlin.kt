package com.openclassrooms.realestatemanager.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PorterDuff
import android.location.Address
import android.location.Geocoder
import android.net.ConnectivityManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.openclassrooms.realestatemanager.BuildConfig
import com.openclassrooms.realestatemanager.CreateProperty.Companion.PERMISSION_CODE_READ
import com.openclassrooms.realestatemanager.CreateProperty.Companion.PERMISSION_CODE_WRITE
import com.openclassrooms.realestatemanager.CreateProperty.Companion.REQUEST_PERMISSION
import com.openclassrooms.realestatemanager.R
import java.io.IOException

class UtilsKotlin {
    companion object {


        const val API_KEY = BuildConfig.API_KEY
        const val baseUrl:String = "https://maps.googleapis.com/maps/api/"
        const val radius:Int = 50000

        fun verifyAvailableNetwork(activity: AppCompatActivity): Boolean {
            val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected

        }

        // draw marker
        fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor {
            val background = ContextCompat.getDrawable(context, R.drawable.ic_location)
            background!!.setBounds(0, 0, background.intrinsicWidth, background.intrinsicHeight)
            val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)
            vectorDrawable!!.setBounds(24, 24, vectorDrawable.intrinsicWidth + 12, vectorDrawable.intrinsicHeight + 12)
            val bitmap = Bitmap.createBitmap(background.intrinsicWidth, background.intrinsicHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            vectorDrawable.setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN)
            background.draw(canvas)
            vectorDrawable.draw(canvas)
            return BitmapDescriptorFactory.fromBitmap(bitmap)
        }

        // permissions camera
        fun checkPermission(activity: AppCompatActivity) {
            if (checkSelfPermission(activity, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity,
                        arrayOf(Manifest.permission.CAMERA),
                        REQUEST_PERMISSION)
            }
        }

        fun checkPermissionForImage(activity: AppCompatActivity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if ((checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
                        && (checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)) {
                    ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), PERMISSION_CODE_READ)
                    ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), PERMISSION_CODE_WRITE)

                }
            }
        }


        // get lat and lng from address
        fun getLocationFromAddress(activity: AppCompatActivity, strAddress: String): LatLng? {
            val coder = Geocoder(activity)
            val address: List<Address>?
            var p1: LatLng? = null
            try {
                // May throw an IOException
                address = coder.getFromLocationName(strAddress, 3)
                if (address == null) {
                    return null
                }
                val location = address[0]
                p1 = LatLng(location.latitude, location.longitude)

            } catch (ex: IOException) {

                ex.printStackTrace()
            }

            println(p1)
            return p1
        }
    }


}










