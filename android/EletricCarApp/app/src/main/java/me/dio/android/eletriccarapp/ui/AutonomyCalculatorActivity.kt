package me.dio.android.eletriccarapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.dio.android.eletriccarapp.R
import me.dio.android.eletriccarapp.databinding.ActivityAutonomyCalculatorBinding
import java.math.RoundingMode

class AutonomyCalculatorActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAutonomyCalculatorBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupListeners()
        setupCachedAutonomyResult()
    }

    private fun setupListeners() {
        binding.btnCalculate.setOnClickListener {
            calculate()
        }
        binding.ivClose.setOnClickListener {
            this.finish()
        }
    }

    private fun setupCachedAutonomyResult() {
        val calculatedValue = getSharedPref()
        binding.tvAutonomyResult.text = calculatedValue.toString()
    }

    private fun calculate() {
        val pricePerKwhText = binding.etKwhPrice.text.toString().toBigDecimal()
        val kmTravelerText = binding.etKmTraveled.text.toString().toBigDecimal()
        val autonomy = pricePerKwhText.divide(kmTravelerText, 3, RoundingMode.HALF_UP)
        binding.tvAutonomyResult.text = String.format("%.3f", autonomy)
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
