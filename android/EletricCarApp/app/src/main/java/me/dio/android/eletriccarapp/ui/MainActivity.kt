package me.dio.android.eletriccarapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import me.dio.android.eletriccarapp.R
import me.dio.android.eletriccarapp.data.CarFactory
import me.dio.android.eletriccarapp.ui.adapter.CarAdapter

class MainActivity : AppCompatActivity() {
    lateinit var btnCalculatorRedirect : Button
    lateinit var carsList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        setupListeners()
        setupList()
    }

    fun setupView() {
        btnCalculatorRedirect = findViewById(R.id.btn_calculator_redirect)
        carsList = findViewById(R.id.rv_cars_list)
    }

    fun setupListeners() {
        btnCalculatorRedirect.setOnClickListener {
            startActivity(Intent(this, AutonomyCalculatorActivity::class.java))
        }
    }

    fun setupList() {
        var data = CarFactory.list
        var adapter = CarAdapter(data)
        carsList.adapter = adapter
    }
}
