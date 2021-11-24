package com.example.fooddelivery.presenter.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.fooddelivery.R
import com.example.fooddelivery.databinding.ScreenInfoBinding
import com.example.fooddelivery.model.FoodData
import com.example.fooddelivery.presenter.viewmodel.InfoViewModel
import com.example.fooddelivery.presenter.viewmodelImpl.InfoViewModelImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoScreen : Fragment(R.layout.screen_info) {
    private val binding by viewBinding(ScreenInfoBinding::bind)
    private lateinit var data : FoodData
    private val viewModel: InfoViewModel by viewModels<InfoViewModelImpl>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        arguments.let {
            data = it?.getSerializable("data") as FoodData
            Glide.with(binding.ivFoodImage.context)
                .load(data.imageURL)
                .into(binding.ivFoodImage)
            binding.nameProduct.text = data.name
            binding.costProduct.text = "${data.cost} so`m"
        }
        var state = data.isSelected

        if( data.isSelected )
            binding.addFav.setBackgroundResource(R.drawable.bascet_background)
        else
            binding.addFav.setBackgroundResource(R.drawable.delete_background)
        binding.addFav.setOnClickListener {
            if( state )
            binding.addFav.setBackgroundResource(R.drawable.bascet_background)
            viewModel.addToFavourite(data.id)
        }
        binding.deleteFav.setOnClickListener {
            viewModel.deleteFromFavourite(data.id)
            binding.addFav.setBackgroundResource(R.drawable.delete_background)
        }

    }
}