package com.example.fooddelivery.presenter.viewmodelImpl

import androidx.lifecycle.ViewModel
import com.example.fooddelivery.data.repository.AppRepository
import com.example.fooddelivery.model.FoodData
import com.example.fooddelivery.model.LocationData
import com.example.fooddelivery.presenter.viewmodel.FavouriteViewModel
import com.example.fooddelivery.presenter.viewmodel.LocationViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModelImpl @Inject constructor(
    private val repository: AppRepository
) : ViewModel(), FavouriteViewModel {
    override fun getFavourites(): List<FoodData> = repository.favouriteList
}