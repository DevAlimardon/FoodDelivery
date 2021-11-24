package com.example.fooddelivery

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.fooddelivery.databinding.ScreenMainNavBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ScreenMainNavBinding
    private var pos = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ScreenMainNavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.include.bottomMenu

        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.algorithms -> {
                    binding.navDrawer.closeDrawer(GravityCompat.START)
                }
                R.id.courses -> {
                    binding.navDrawer.closeDrawer(GravityCompat.START)
                }
                R.id.profiles -> {
                    binding.navDrawer.closeDrawer(GravityCompat.START)
                }
            }
            true
        }
        binding.include.ivMenu.setOnClickListener {
            binding.navDrawer.openDrawer(GravityCompat.START)
        }
        val navController = findNavController(R.id.appNav)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.mainScreen,
                R.id.selectedScreen,
                R.id.menuScreen,
                R.id.restaurantScreen,
                R.id.profileScreen
            )
        )
        navView.setupWithNavController(navController)
    }
}
