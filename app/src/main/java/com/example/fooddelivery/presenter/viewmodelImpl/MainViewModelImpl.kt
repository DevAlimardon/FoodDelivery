package com.example.fooddelivery.presenter.viewmodelImpl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddelivery.data.repository.AppRepository
import com.example.fooddelivery.model.AdsData
import com.example.fooddelivery.model.FoodData
import com.example.fooddelivery.presenter.viewmodel.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(
    private val repository: AppRepository
) : ViewModel(),
    MainViewModel {
    override val currentPageLiveData = MutableLiveData<Int>()
    override val adsListLiveData = MutableLiveData<List<AdsData>>()
    override val popularListLiveData = MutableLiveData<List<FoodData>>()
    private var pos = 0

    init {
        viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                if (pos == repository.adsData.size)
                    pos = 0
                delay(1500)
                currentPageLiveData.postValue(pos++)
            }
        }
        repository.setChangeCountListener {
            getPopular()
        }
    }

    override fun increaseCount(id: Long) {
        repository.increaseCount(id)
    }

    override fun decreaseCount(id: Long) {
        repository.decreaseCount(id)
    }

    override fun getAds() {
        adsListLiveData.value = repository.adsData
    }

    override fun getPopular() {
        popularListLiveData.value = repository.popularList
    }

}