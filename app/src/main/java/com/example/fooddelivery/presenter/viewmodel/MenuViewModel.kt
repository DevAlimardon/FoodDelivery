package com.example.fooddelivery.presenter.viewmodel

import androidx.lifecycle.LiveData
import com.example.fooddelivery.model.FoodData

interface MenuViewModel {
    val allFoodsLiveData : LiveData<List<FoodData>>

    fun getAllFood()
    fun increaseCount(id : Long)
    fun decreaseCount(id : Long)
}