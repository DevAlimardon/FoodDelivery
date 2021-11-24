package com.example.fooddelivery.presenter.viewmodelImpl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddelivery.data.repository.AppRepository
import com.example.fooddelivery.presenter.viewmodel.SplashViewModel
import com.google.firestore.v1.FirestoreGrpc
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(
    private val repository: AppRepository
    ) : ViewModel(),
    SplashViewModel {
    override val openMainLiveData  = MutableLiveData<Unit>()
    private var count = 0

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.successLoadListener {
                count++
                if (count == 2) {
                openMainLiveData.value = Unit
                }
            }
        }


       /*val favouriteFoodLiveData = MutableLiveData<List<FoodData>>()
        viewModelScope.launch(Dispatchers.IO) {
            favouriteFoodLiveData.postValue(repository.foodsData.filter { it.isFavourite })
        }*/
    }
}