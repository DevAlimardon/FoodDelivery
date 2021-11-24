package com.example.fooddelivery.presenter.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fooddelivery.R
import com.example.fooddelivery.databinding.ScreenMainBinding
import com.example.fooddelivery.model.AdsData
import com.example.fooddelivery.model.FoodData
import com.example.fooddelivery.presenter.adapter.MenuAdapter
import com.example.fooddelivery.presenter.adapter.PagingAdapter
import com.example.fooddelivery.presenter.viewmodel.MainViewModel
import com.example.fooddelivery.presenter.viewmodelImpl.MainViewModelImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main) {
    private val binding by viewBinding(ScreenMainBinding::bind)
    private val adapter by lazy { MenuAdapter() }
    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.rvList.adapter = adapter
        binding.rvList.layoutManager = GridLayoutManager(requireContext(), 2)
        viewModel.getPopular()
        viewModel.getAds()

        adapter.setItemClickListener {
        }
        adapter.setIncreaseItemClickListener {
            viewModel.increaseCount(it)
        }
        adapter.setDecreaseItemClickListener {
            viewModel.decreaseCount(it)
        }
        binding.pager.isUserInputEnabled = false
        viewModel.currentPageLiveData.observe(viewLifecycleOwner, currentPageObserver)
        viewModel.adsListLiveData.observe(viewLifecycleOwner,adsListObserver)
        viewModel.popularListLiveData.observe(viewLifecycleOwner,popularListObserver)
    }

    private val currentPageObserver = Observer<Int> {
        binding.pager.setCurrentItem(it, false)
    }

    private val adsListObserver = Observer<List<AdsData>> {
        binding.pager.adapter = PagingAdapter(childFragmentManager, lifecycle,it)
        binding.tabLayout.setViewPager2(binding.pager)
    }

    private val popularListObserver = Observer<List<FoodData>> {
        adapter.submitList(it)
        adapter.notifyDataSetChanged()
    }
}