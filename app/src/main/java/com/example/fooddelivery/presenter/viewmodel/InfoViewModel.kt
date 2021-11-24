package com.example.fooddelivery.presenter.viewmodel

import com.example.fooddelivery.model.FoodData
import com.example.fooddelivery.model.LocationData

interface InfoViewModel {
    fun getFavourites() : List<FoodData>
    fun addToFavourite(pos: Long)
    fun deleteFromFavourite(pos: Long)
    fun getState():Boolean
}