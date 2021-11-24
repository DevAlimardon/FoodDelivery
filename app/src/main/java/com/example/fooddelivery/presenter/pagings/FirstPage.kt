package com.example.fooddelivery.presenter.pagings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.fooddelivery.R
import com.example.fooddelivery.databinding.PageFirstBinding

class FirstPage : Fragment(R.layout.page_first) {
    private val binding by viewBinding(PageFirstBinding::bind)

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageUrl = requireArguments().getString("Image", "")

        Glide.with(requireContext())
            .load(imageUrl)
            .placeholder(R.drawable.secondimage)
            .into(binding.image)
    }

}