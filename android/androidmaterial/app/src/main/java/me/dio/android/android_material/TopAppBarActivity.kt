package me.dio.android.android_material

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.dio.android.android_material.databinding.ActivityTopAppBarBinding
import me.dio.android.android_material.extensions.toast

class TopAppBarActivity : AppCompatActivity() {
    private val binding by lazy { ActivityTopAppBarBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.topAppBar.setNavigationOnClickListener {
            toast("Clicou no menu de navegação")
        }

        binding.topAppBar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.edit -> {
                    toast("Clicou edit")
                    true
                }
                R.id.favorite -> {
                    toast("Clicou favorite")
                    true
                }
                R.id.more -> {
                    toast("Clicou more")
                    true
                }
                else -> false
            }
        }
    }

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, TopAppBarActivity::class.java)
    }
}
