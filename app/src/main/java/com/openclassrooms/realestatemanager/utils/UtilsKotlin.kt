package com.openclassrooms.realestatemanager.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class UtilsKotlin {

    companion object {

        fun verifyAvailableNetwork(activity: AppCompatActivity): Boolean {
            val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected

        }
    }

   //fun checkPermission(activity: AppCompatActivity) {
   //    if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
   //            != PackageManager.PERMISSION_GRANTED) {
   //        ActivityCompat.requestPermissions(activity,
   //                arrayOf(Manifest.permission.CAMERA),
   //                REQUEST_PERMISSION)
   //    }
   //}

}

