package com.example.fooddelivery.presenter.viewmodel

import com.example.fooddelivery.model.LocationData

interface LocationViewModel {
    fun getLocations() : List<LocationData>
    fun getCategories() : List<String>

}