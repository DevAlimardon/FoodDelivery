package com.example.fooddelivery.presenter.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fooddelivery.R
import com.example.fooddelivery.databinding.ScreenMenuBinding
import com.example.fooddelivery.model.FoodData
import com.example.fooddelivery.presenter.adapter.MenuAdapter
import com.example.fooddelivery.presenter.viewmodel.MenuViewModel
import com.example.fooddelivery.presenter.viewmodelImpl.MenuViewModelImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuScreen : Fragment(R.layout.screen_menu) {
    private val binding by viewBinding(ScreenMenuBinding::bind)
    private val adapter by lazy { MenuAdapter() }
    private val viewModel: MenuViewModel by viewModels<MenuViewModelImpl>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.rvList.adapter = adapter
        binding.rvList.layoutManager = GridLayoutManager(requireContext(), 2)
        viewModel.getAllFood()

        adapter.setItemClickListener {
            val bundle = Bundle()
            bundle.putSerializable("data", it)
            findNavController().navigate(R.id.action_menuScreen_to_infoScreen, bundle)
        }
        adapter.setIncreaseItemClickListener {
            viewModel.increaseCount(it)
        }
        adapter.setDecreaseItemClickListener {
            viewModel.decreaseCount(it)
        }
        viewModel.allFoodsLiveData.observe(viewLifecycleOwner, allFoodsObserver)
    }

    private val allFoodsObserver = Observer<List<FoodData>> {
        adapter.submitList(it)
        adapter.notifyDataSetChanged()
    }

}