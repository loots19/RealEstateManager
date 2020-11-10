package com.openclassrooms.realestatemanager.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapsFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    companion object {
        fun newInstance(): MapsFragment = MapsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(com.openclassrooms.realestatemanager.R.layout.fragment_maps, container, false)
        val mapFragment = childFragmentManager.fragments[0] as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
        return rootView
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap!!
        val sydney = LatLng(48.8534, 2.3488)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Paris"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10f))

    }
}
