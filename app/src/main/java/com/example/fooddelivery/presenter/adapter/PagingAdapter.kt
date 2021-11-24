package com.example.fooddelivery.presenter.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fooddelivery.model.AdsData
import com.example.fooddelivery.presenter.pagings.FirstPage

class PagingAdapter(
    fg: FragmentManager, lifecycle: Lifecycle, private val imageList: List<AdsData>
) : FragmentStateAdapter(fg, lifecycle) {
    override fun getItemCount(): Int = imageList.size

    override fun createFragment(position: Int): Fragment {
        val fm = FirstPage()
        val bundle = Bundle()
            bundle.putString("Image", imageList[position].imageURL)
            fm.arguments = bundle
            return fm
    }
}