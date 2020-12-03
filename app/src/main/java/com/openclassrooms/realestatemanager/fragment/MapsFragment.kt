package com.openclassrooms.realestatemanager.fragment

import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import com.openclassrooms.realestatemanager.R
import com.openclassrooms.realestatemanager.detailActivity.DetailActivity
import com.openclassrooms.realestatemanager.detailActivity.DetailActivity.Companion.EXTRA_PROPERTY
import com.openclassrooms.realestatemanager.model.Property
import com.openclassrooms.realestatemanager.utils.UtilsKotlin
import com.openclassrooms.realestatemanager.viewModels.PropertyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapsFragment : Fragment(), OnMapReadyCallback {
    private lateinit var currentLocation: Location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var mMap: GoogleMap
    private val mPropertyViewModel by viewModel<PropertyViewModel>()


    companion object {
        fun newInstance(): MapsFragment = MapsFragment()
        const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_maps, container, false)

        val mapFragment = childFragmentManager.fragments[0] as SupportMapFragment?
        mapFragment!!.getMapAsync(this)


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        return rootView
    }

    private fun fetchLocation() {
        UtilsKotlin.checkLocationPermission(requireActivity() as AppCompatActivity)
        mMap.isMyLocationEnabled = true
        fusedLocationProviderClient.lastLocation.addOnSuccessListener(requireActivity()) { location ->
            // Got last known location. In some rare situations this can be null.
            if (location != null) {
                currentLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))

            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        fetchLocation()
        mPropertyViewModel.getAllProperty().observe(this, androidx.lifecycle.Observer {
            if (it != null) {
                generateMarker(it)

            }
        })

        mMap.setOnMarkerClickListener {
            lunchDetailActivity(it)
        }
    }
    // ------------------------------------------
    // ----- Add marker on map with address -----
    // ------------------------------------------
    private fun generateMarker(address: List<Property>) {
        for (i in address.indices) {
            val addressTxt = address[i].address + address[i].city
            val addressLatLng = UtilsKotlin.getLocationFromAddress(requireActivity() as AppCompatActivity, addressTxt)
            val marker = mMap.addMarker(addressLatLng?.let {

                MarkerOptions()
                        .position(it)
                        .title(addressTxt)
                        .icon(UtilsKotlin.bitmapDescriptorFromVector(requireContext(), R.drawable.ic_location_city_black_24dp))

            })
            val gson = Gson()
            val jsonSelectedRestaurant = gson.toJson(address[i])
            marker.tag = jsonSelectedRestaurant
        }
    }

    // ----------------------------------
    // ----- Launch detail activity -----
    // ----------------------------------
    private fun lunchDetailActivity(marker: Marker): Boolean {
        val property = marker.tag as String?
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(EXTRA_PROPERTY, property)
        startActivity(intent)
        return true
    }


}