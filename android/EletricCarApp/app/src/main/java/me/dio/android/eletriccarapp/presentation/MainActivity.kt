package me.dio.android.eletriccarapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import me.dio.android.eletriccarapp.R

class MainActivity : AppCompatActivity() {
    lateinit var btnCalculatorRedirect : Button
    lateinit var list: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        setupListeners()
        setupList()
    }

    fun setupView() {
        btnCalculatorRedirect = findViewById(R.id.btn_calculator_redirect)
        list = findViewById(R.id.lv_informations)
    }

    fun setupListeners() {
        btnCalculatorRedirect.setOnClickListener {
            startActivity(Intent(this, AutonomyCalculatorActivity::class.java))
        }
    }

    fun setupList() {
        var data = arrayOf("Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread", "Honeycomb", "Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lollipop", "Marshmallow", "Nougat", "Oreo", "Pie")
        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data)
        list.adapter = adapter
    }
}
