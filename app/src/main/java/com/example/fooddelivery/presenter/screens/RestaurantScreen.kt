package com.example.fooddelivery.presenter.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ahmadhamwi.tabsync.TabbedListMediator
import com.example.fooddelivery.R
import com.example.fooddelivery.databinding.ScreenResturantBinding
import com.example.fooddelivery.model.LocationData
import com.example.fooddelivery.presenter.adapter.LocationAdapter
import com.example.fooddelivery.presenter.viewmodel.LocationViewModel
import com.example.fooddelivery.presenter.viewmodelImpl.LocationViewModelImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantScreen : Fragment(R.layout.screen_resturant) {
    private val binding by viewBinding(ScreenResturantBinding::bind)

    private val adapter by lazy { LocationAdapter() }
    private lateinit var data: LocationData
    private val viewModel: LocationViewModel by viewModels<LocationViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvList.adapter = adapter
        binding.rvList.layoutManager = LinearLayoutManager(requireContext())
        adapter.submitList(viewModel.getLocations())

        adapter.setLocationItemListener {
            data = it
            val bundle = Bundle()
            bundle.putSerializable("data", data)
            findNavController().navigate(R.id.action_restaurantScreen_to_mapFragment, bundle)
        }
        for (category in viewModel.getCategories()) {
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(category))
        }
        TabbedListMediator(
            binding.rvList,
            binding.tabLayout,
            viewModel.getCategories().indices.toList()
        ).attach()
    }
}