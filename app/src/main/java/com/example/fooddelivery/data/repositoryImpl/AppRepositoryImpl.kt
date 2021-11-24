package com.example.fooddelivery.data.repositoryImpl

import com.example.fooddelivery.data.repository.AppRepository
import com.example.fooddelivery.model.AdsData
import com.example.fooddelivery.model.FoodData
import com.example.fooddelivery.model.LocationData
import com.example.fooddelivery.model.TabData
import com.example.fooddelivery.pref.SharedPref
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val pref: SharedPref,
    private val repository: FirebaseFirestore
) : AppRepository {
    override val adsData: ArrayList<AdsData> = ArrayList()
    override val foodsData: ArrayList<FoodData> = ArrayList()
    override val locationsData: ArrayList<LocationData> = ArrayList()

    override val categoryLocation: ArrayList<String> = ArrayList()
    override val popularList: List<FoodData> get() = foodsData.filter { it.isFavourite }
    override val favouriteList: List<FoodData> get() = foodsData.filter { it.isSelected }
    override val tabList = ArrayList<TabData>()
    override val clickToBuy: HashMap<String, Int> = HashMap()
    private var successLoadListener: (() -> Unit)? = null
    private var errorLoadListener: (() -> Unit)? = null

    init {
        loadTabData()
        getAdsProduct()
        getAllFoods()
        getAllLocations()
    }

    private fun loadTabData() {
        tabList.add(TabData("TASHKENT", 1))
        tabList.add(TabData("FARGONA", 2))
        //...
    }

    /*
    enum class LocationEnum(val pos: Int) {
    TASHKENT(1),
    FARGONA(2),
    QASHQADARYO(3),
    ANDIJON(4),
    QOQON(5),
    NAMANGAN(6),
    SAMARQAND(7)
}
    * */
    override fun successLoadListener(block: () -> Unit) {
        successLoadListener = block
    }

    override fun errorLoadListener(block: () -> Unit) {
        errorLoadListener = block
    }

    override fun addFavouriteItem(pos: Long) {
        foodsData.forEach {
            if (it.id == pos)
                it.isSelected = true
        }
    }

    override fun deleteFavouriteItem(pos: Long) {
        foodsData.forEach {
            if (it.id == pos)
                it.isSelected = false
        }
    }

    override fun increaseCount(id: Long) {
        foodsData.forEach {
            if (it.id == id)
                it.count++
        }
        changeCountListener?.invoke()
    }

    override fun decreaseCount(id: Long) {
        foodsData.forEach {
            if (it.id == id)
                it.count--
        }
        changeCountListener?.invoke()
    }

    private var changeCountListener: (() -> Unit)? = null

    override fun setChangeCountListener(block: () -> Unit) {
        changeCountListener = block
    }

    override fun getLocationByType(type: Long): List<LocationData> = locationsData.filter { it.type == type }

    private fun getAdsProduct() {
        repository.collection("ads")
            .get()
            .addOnSuccessListener {
                for (document in it) {
                    val id = document["id"] as Long
                    val imageURL = document["imageURL"] as String
                    adsData.add(AdsData(id, imageURL))
                }
                successLoadListener?.invoke()
            }
            .addOnFailureListener {
            }
    }

    private fun getAllFoods() {
        repository.collection("foods")
            .get()
            .addOnSuccessListener { result ->
                result.onEach { document ->
                    val id = document["id"] as Long
                    val name = document["name"] as String
                    val cost = document["cost"] as Long
                    val imageURL = document["imageURL"] as String
                    val type = document["type"] as Long
                    val isFavourite = document["isFavourite"] as Boolean
                    val data = FoodData(id, name, cost, imageURL, type, isFavourite)
                    foodsData.add(data)
                }
                foodsData.sortBy { it.type }
                successLoadListener?.invoke()
            }
            .addOnFailureListener {
                errorLoadListener?.invoke()
            }
    }

    private fun getAllLocations() {
        repository.collection("locations")
            .get()
            .addOnSuccessListener { result ->
                result.onEach { document ->
                    val id = document["id"] as Long
                    val description = document["description"] as String
                    val latitude = document["latitude"] as Double
                    val longitude = document["longitude"] as Double
                    val time = document["time"] as String
                    val name = document["name"] as String
                    val type = document["type"] as Long
                    val data = LocationData(id, name, description, time, latitude, longitude, type)
                    locationsData.add(data)
                    categoryLocation.add(data.type.toString())
                }
                successLoadListener?.invoke()
            }
            .addOnFailureListener {
                errorLoadListener?.invoke()
            }
    }
}