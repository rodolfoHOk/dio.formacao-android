package me.dio.android.android_material

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.dio.android.android_material.databinding.ActivityTextFieldsBinding

class TextFieldsActivity : AppCompatActivity() {
    private val binding by lazy { ActivityTextFieldsBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.tilWithErrorText.error = "Campo obrigatório"
    }

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, TextFieldsActivity::class.java)
    }
}
