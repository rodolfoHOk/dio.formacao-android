package me.dio.android.eletriccarapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import me.dio.android.eletriccarapp.R
import me.dio.android.eletriccarapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupListeners()

        val navController = findNavController(R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)
    }

    private fun setupListeners() {
        binding.fabCalculate.setOnClickListener {
            startActivity(Intent(this, AutonomyCalculatorActivity::class.java))
        }
    }
}
