package me.dio.android.lifecycle.presentation.streaming

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class LiveActivity : AppCompatActivity() {
    private val liveVideo = LiveVideo()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        lifecycle.addObserver(liveVideo)
    }
}
