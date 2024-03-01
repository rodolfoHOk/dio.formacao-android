package me.dio.android.eletriccarapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
        Log.d("LifeCycle: ", "CREATE")
    }

    override fun onStart() {
        super.onStart()
        Log.d("LifeCycle: ", "START")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LifeCycle: ", "RESUME")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LifeCycle: ", "PAUSE")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LifeCycle: ", "STOP")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LifeCycle: ", "DESTROY")
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
