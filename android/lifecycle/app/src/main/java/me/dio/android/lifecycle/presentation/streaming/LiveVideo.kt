package me.dio.android.lifecycle.presentation.streaming

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class LiveVideo : DefaultLifecycleObserver {
    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        // play video
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        // pause video
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        // frees video memory
    }
}
