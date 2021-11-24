package com.example.fooddelivery.presenter.viewmodelImpl

import androidx.lifecycle.ViewModel
import com.example.fooddelivery.data.repository.AppRepository
import com.example.fooddelivery.model.LocationData
import com.example.fooddelivery.presenter.viewmodel.LocationViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationViewModelImpl @Inject constructor(
    private val repository: AppRepository
) : ViewModel(), LocationViewModel {
    override fun getLocations(): List<LocationData> = repository.locationsData
    override fun getCategories(): List<String> = repository.categoryLocation
}