package com.example.fooddelivery.presenter.viewmodelImpl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fooddelivery.data.repository.AppRepository
import com.example.fooddelivery.model.FoodData
import com.example.fooddelivery.presenter.viewmodel.MenuViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuViewModelImpl @Inject constructor(
    private val repository: AppRepository
) : ViewModel(),
    MenuViewModel {

    override val allFoodsLiveData= MutableLiveData<List<FoodData>>()

    init {
        repository.setChangeCountListener {
            getAllFood()
        }
    }
    override fun getAllFood(){
        allFoodsLiveData.value = repository.foodsData
    }

    override fun increaseCount(id: Long) {
        repository.increaseCount(id)
    }

    override fun decreaseCount(id: Long) {
        repository.decreaseCount(id)
    }
}