package me.dio.android.eletriccarapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import me.dio.android.eletriccarapp.R
import me.dio.android.eletriccarapp.data.CarFactory
import me.dio.android.eletriccarapp.ui.adapter.CarAdapter
import me.dio.android.eletriccarapp.ui.adapter.TabsAdapter

class MainActivity : AppCompatActivity() {
    lateinit var btnCalculatorRedirect : Button
    lateinit var carsList: RecyclerView
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        setupTabs()
        setupListeners()
        setupList()
    }

    fun setupView() {
        btnCalculatorRedirect = findViewById(R.id.btn_calculator_redirect)
        carsList = findViewById(R.id.rv_cars_list)
        tabLayout = findViewById(R.id.tab_layout_main)
    }

    fun setupTabs() {
        val tabsAdapter = TabsAdapter(this)
        viewPager.adapter = tabsAdapter
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
