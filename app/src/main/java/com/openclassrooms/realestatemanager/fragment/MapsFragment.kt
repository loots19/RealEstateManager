package com.openclassrooms.realestatemanager.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapsFragment : Fragment(), OnMapReadyCallback {
    private lateinit var currentLocation: Location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var mMap: GoogleMap

    companion object {
        fun newInstance(): MapsFragment = MapsFragment()
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(com.openclassrooms.realestatemanager.R.layout.fragment_maps, container, false)

        val mapFragment = childFragmentManager.fragments[0] as SupportMapFragment?
        mapFragment!!.getMapAsync(this)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        return rootView
    }

    private fun fetchLocation() {
        if (ContextCompat.checkSelfPermission(requireActivity(),
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(),
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
        }
        mMap.isMyLocationEnabled = true
        fusedLocationProviderClient.lastLocation.addOnSuccessListener(requireActivity()) { location ->

            if (location != null) {
                currentLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                val markerOptions = MarkerOptions().position(currentLatLng).title("I am here!")
                mMap.addMarker(markerOptions)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
            }
        }


    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fetchLocation()
        }

    }

}