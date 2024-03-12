package me.dio.android.android_material

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import me.dio.android.android_material.databinding.ActivitySnackbarBinding

class SnackBarActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySnackbarBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.mbShowing.setOnClickListener {
            Snackbar.make(it, "Click no primeiro botão", Snackbar.LENGTH_SHORT).show()
        }

        binding.mbAction.setOnClickListener {
            Snackbar.make(it, "O segundo botão foi clicado", Snackbar.LENGTH_SHORT)
                .setAction("Go") {
                    Log.d("TAG", "onClick: clicou na ação")
                }
                .show()
        }
    }

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, SnackBarActivity::class.java)
    }
}
