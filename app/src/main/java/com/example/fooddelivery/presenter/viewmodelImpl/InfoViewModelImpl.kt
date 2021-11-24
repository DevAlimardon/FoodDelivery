package com.example.fooddelivery.presenter.viewmodelImpl

import androidx.lifecycle.ViewModel
import com.example.fooddelivery.data.repository.AppRepository
import com.example.fooddelivery.model.FoodData
import com.example.fooddelivery.model.LocationData
import com.example.fooddelivery.presenter.viewmodel.FavouriteViewModel
import com.example.fooddelivery.presenter.viewmodel.InfoViewModel
import com.example.fooddelivery.presenter.viewmodel.LocationViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InfoViewModelImpl @Inject constructor(
    private val repository: AppRepository
) : ViewModel(), InfoViewModel {
    override fun getFavourites(): List<FoodData> = repository.favouriteList
    override fun addToFavourite(pos: Long) = repository.addFavouriteItem(pos)
    override fun deleteFromFavourite(pos: Long) = repository.deleteFavouriteItem(pos)
    override fun getState(): Boolean {
        TODO("Not yet implemented")
    }
}