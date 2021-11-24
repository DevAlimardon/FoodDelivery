package com.example.fooddelivery.presenter.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fooddelivery.R
import com.example.fooddelivery.databinding.ScreenSelectedBinding
import com.example.fooddelivery.presenter.adapter.FavouriteAdapter
import com.example.fooddelivery.presenter.adapter.MenuAdapter
import com.example.fooddelivery.presenter.viewmodel.FavouriteViewModel
import com.example.fooddelivery.presenter.viewmodelImpl.FavouriteViewModelImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectedScreen : Fragment(R.layout.screen_selected) {

    private val binding by viewBinding(ScreenSelectedBinding::bind)
    private val adapter by lazy { FavouriteAdapter() }

    private val viewModel : FavouriteViewModel by viewModels<FavouriteViewModelImpl>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.rvList.adapter = adapter
        adapter.submitList(viewModel.getFavourites())

    }
}