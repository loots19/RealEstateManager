package com.openclassrooms.realestatemanager.utils

import android.Manifest
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService

class UtilsKotlin {
    private lateinit var channelId : String
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



