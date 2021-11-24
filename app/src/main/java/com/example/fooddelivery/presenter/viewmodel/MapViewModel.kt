package com.example.fooddelivery.presenter.viewmodel

import com.example.fooddelivery.model.LocationData

interface MapViewModel {
    fun getLocations() : List<LocationData>
}