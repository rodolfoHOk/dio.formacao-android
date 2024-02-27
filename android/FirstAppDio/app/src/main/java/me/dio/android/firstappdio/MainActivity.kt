package me.dio.android.firstappdio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        soma()
//        throw Exception("Meu primeiro erro")
    }

    fun soma() : Int {
        return 1 + 1
    }
}
