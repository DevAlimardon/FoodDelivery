package com.example.fooddelivery.presenter.viewmodelImpl

import androidx.lifecycle.ViewModel
import com.example.fooddelivery.data.repository.AppRepository
import com.example.fooddelivery.model.LocationData
import com.example.fooddelivery.presenter.viewmodel.LocationViewModel
import com.example.fooddelivery.presenter.viewmodel.MapViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModelImpl @Inject constructor(
    private val repository: AppRepository
) : ViewModel(), MapViewModel {
    override fun getLocations(): List<LocationData> = repository.locationsData
}