package com.example.fooddelivery.presenter.screens

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fooddelivery.InfoDialog
import com.example.fooddelivery.R
import com.example.fooddelivery.databinding.ScreenMapBinding
import com.example.fooddelivery.model.LocationData
import com.example.fooddelivery.model.getDrawableResources
import com.example.fooddelivery.presenter.viewmodel.LocationViewModel
import com.example.fooddelivery.presenter.viewmodel.MapViewModel
import com.example.fooddelivery.presenter.viewmodelImpl.LocationViewModelImpl
import com.example.fooddelivery.presenter.viewmodelImpl.MapViewModelImpl
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFragment: Fragment(R.layout.screen_map), OnMapReadyCallback {

    private val binding by viewBinding(ScreenMapBinding::bind)
    private lateinit var mMap: GoogleMap
    private lateinit var data : LocationData
    private val viewModel: MapViewModel by viewModels<MapViewModelImpl>()

    private val list  = ArrayList<LocationData>()
    private lateinit var sampleList : List<LocationData>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment.getMapAsync(this)
        data = arguments?.getSerializable("data") as LocationData
        sampleList = viewModel.getLocations()

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        loadData(sampleList)
        addMarkers()
        mMap.setOnMarkerClickListener { marker ->
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.position,18f))
            val dialog = InfoDialog()
            val bundle = Bundle()
            bundle.putSerializable("data",findLocationByLatLng(marker.position))
            dialog.arguments = bundle
            dialog.show(childFragmentManager,"infoDialog")
            return@setOnMarkerClickListener true
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(data.latitude,data.longitude),18f))
    }
    private fun findLocationByLatLng(latLng : LatLng) : LocationData {
        return list.first { it.latitude == latLng.latitude && it.longitude == latLng.longitude }
    }
    private fun loadData(locations: List<LocationData>) {
        list.addAll(locations)
    }
    private fun addMarkers(){
        for (element in list) {
            val latLng = LatLng(element.latitude,element.longitude)
            mMap.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .title(element.name)
                    .icon(BitmapDescriptorFactory.fromResource(element.category.getDrawableResources())))
        }
    }
}