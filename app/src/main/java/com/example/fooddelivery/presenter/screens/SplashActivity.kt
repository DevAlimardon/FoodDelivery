package com.example.fooddelivery.presenter.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddelivery.MainActivity
import com.example.fooddelivery.databinding.ActivitySplashBinding
import com.example.fooddelivery.presenter.viewmodel.SplashViewModel
import com.example.fooddelivery.presenter.viewmodelImpl.SplashViewModelImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val viewModel: SplashViewModel by viewModels<SplashViewModelImpl>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.openMainLiveData.observe(this, {
            val intent = Intent(this, MainActivity::class.java)
            viewModel.openMainLiveData
            startActivity(intent)
        })
    }
}