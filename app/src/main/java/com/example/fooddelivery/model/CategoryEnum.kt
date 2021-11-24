package com.example.fooddelivery.model

import com.example.fooddelivery.R

enum class CategoryEnum {
    EVOS,DEFAULT
}

fun CategoryEnum.getDrawableResources() : Int {
    return when(this){
        CategoryEnum.EVOS -> R.drawable.location
        else ->R.drawable.location
    }
}