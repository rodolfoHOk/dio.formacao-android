package me.dio.android.lifecycle.streaming

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class LiveActivity : AppCompatActivity() {
    private val liveVideo = LiveVideo()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
//        liveVideo.start() // before add DefaultLifecycleObserver in LiveVideo
        lifecycle.addObserver(liveVideo) // after add DefaultLifecycleObserver in LiveVideo
    }

//    override fun onPause() { // before add DefaultLifecycleObserver in LiveVideo
//        super.onPause()
//        liveVideo.stop()
//    }
//
//    override fun onDestroy() { // before add DefaultLifecycleObserver in LiveVideo
//        super.onDestroy()
//        liveVideo.realise()
//    }
}
