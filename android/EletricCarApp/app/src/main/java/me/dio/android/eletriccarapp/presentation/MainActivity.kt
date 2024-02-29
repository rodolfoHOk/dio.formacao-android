package me.dio.android.eletriccarapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import me.dio.android.eletriccarapp.R

class MainActivity : AppCompatActivity() {
    lateinit var btnCalculatorRedirect : Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        setupListeners()
    }

    fun setupView() {
        btnCalculatorRedirect = findViewById<Button>(R.id.btn_calculator_redirect)
    }

    fun setupListeners() {
        btnCalculatorRedirect.setOnClickListener {
            startActivity(Intent(this, AutonomyCalculatorActivity::class.java))
        }
    }
}
