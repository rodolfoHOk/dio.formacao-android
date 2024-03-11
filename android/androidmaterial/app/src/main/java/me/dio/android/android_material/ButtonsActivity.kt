package me.dio.android.android_material

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.dio.android.android_material.databinding.ActivityButtonsBinding

class ButtonsActivity : AppCompatActivity() {
    private val binding by lazy { ActivityButtonsBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, ButtonsActivity::class.java)
    }
}
