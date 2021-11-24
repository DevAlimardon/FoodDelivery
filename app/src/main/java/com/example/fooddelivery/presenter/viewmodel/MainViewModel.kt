package com.example.fooddelivery.presenter.viewmodel

import androidx.lifecycle.LiveData
import com.example.fooddelivery.model.AdsData
import com.example.fooddelivery.model.FoodData

interface MainViewModel {
    val currentPageLiveData: LiveData<Int>
    val adsListLiveData: LiveData<List<AdsData>>
    val popularListLiveData: LiveData<List<FoodData>>

    fun getAds()
    fun getPopular()
    fun increaseCount(id: Long)
    fun decreaseCount(id: Long)

}