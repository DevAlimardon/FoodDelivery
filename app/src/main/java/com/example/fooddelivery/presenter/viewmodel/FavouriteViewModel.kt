package com.example.fooddelivery.presenter.viewmodel

import com.example.fooddelivery.model.FoodData
import com.example.fooddelivery.model.LocationData

interface FavouriteViewModel {
    fun getFavourites() : List<FoodData>

}