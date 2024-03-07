package me.dio.android.eletriccarapp.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import me.dio.android.eletriccarapp.R
import java.math.RoundingMode

class AutonomyCalculatorActivity : AppCompatActivity() {
    private lateinit var pricePerKwh : EditText
    private lateinit var kmTraveler: EditText
    private lateinit var btnCalculate : Button
    private lateinit var autonomyResult: TextView
    private lateinit var btnClose: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_autonomy_calculator)
        setupView()
        setupListeners()
        setupCachedAutonomyResult()
    }

    private fun setupView() {
        pricePerKwh = findViewById<EditText>(R.id.et_kwh_price)
        kmTraveler = findViewById(R.id.et_km_traveled)
        btnCalculate = findViewById<Button>(R.id.btn_calculate)
        autonomyResult = findViewById(R.id.tv_autonomy_result)
        btnClose = findViewById(R.id.iv_close)
    }

    private fun setupListeners() {
        btnCalculate.setOnClickListener {
            calculate()
        }
        btnClose.setOnClickListener {
            finish()
        }
    }

    private fun setupCachedAutonomyResult() {
        val calculatedValue = getSharedPref()
        autonomyResult.text = calculatedValue.toString()
    }

    private fun calculate() {
        val pricePerKwhText = pricePerKwh.text.toString().toBigDecimal()
        val kmTravelerText = kmTraveler.text.toString().toBigDecimal()
        val autonomy = pricePerKwhText.divide(kmTravelerText, 3, RoundingMode.HALF_UP)
        autonomyResult.text = String.format("%.3f", autonomy)
        saveSharedPref(autonomy.toFloat())
    }

    private fun saveSharedPref(result: Float) {
        val sharedPref = getPreferences(MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putFloat(getString(R.string.saved_calc), result)
            apply()
        }
    }

    private fun getSharedPref(): Float {
        val sharedPref = getPreferences(MODE_PRIVATE)
        return sharedPref.getFloat(getString(R.string.saved_calc), 0.0f)
    }
}
