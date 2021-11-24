package com.example.fooddelivery.pref

import android.content.Context
import com.example.fooddelivery.app.App
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPref @Inject constructor() {
    companion object {
        private lateinit var instance: SharedPref
        fun getShared():SharedPref{
            if (!::instance.isInitialized){
                instance = SharedPref()
            }
            return instance
        }
    }

    private val pref = App.instance.getSharedPreferences("FoodDelivery", Context.MODE_PRIVATE)

    var favourite: String
        set(value) {
            pref.edit().putString("favourite", value).apply()
        }
        get() = pref.getString("favourite", "")!!
}