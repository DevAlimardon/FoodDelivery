package com.example.fooddelivery.data.repository

import com.example.fooddelivery.model.AdsData
import com.example.fooddelivery.model.FoodData
import com.example.fooddelivery.model.LocationData
import com.example.fooddelivery.model.TabData


interface AppRepository {
    val adsData: List<AdsData>
    val foodsData : List<FoodData>
    val favouriteList : List<FoodData>
    val popularList : List<FoodData>
    val tabList : List<TabData>

    val categoryLocation : List<String>
    val locationsData : List<LocationData>
    val clickToBuy: HashMap<String,Int>

    fun successLoadListener(block : () -> Unit)
    fun errorLoadListener(block : () -> Unit)


    fun addFavouriteItem(pos : Long)
    fun deleteFavouriteItem(pos : Long)
    fun increaseCount(id : Long)
    fun decreaseCount(id : Long)
    fun setChangeCountListener(block : ()-> Unit)


    fun getLocationByType(type : Long) : List<LocationData>
}

