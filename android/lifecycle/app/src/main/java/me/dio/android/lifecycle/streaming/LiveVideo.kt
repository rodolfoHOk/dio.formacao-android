package me.dio.android.lifecycle.streaming

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class LiveVideo : DefaultLifecycleObserver { // add DefaultLifecycleObserver
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

//    fun start() { // before add DefaultLifecycleObserver
//        // play video
//    }
//
//    fun stop() { // before add DefaultLifecycleObserver
//        // pause video
//    }
//
//    fun realise() { // before add DefaultLifecycleObserver
//        // frees video memory
//    }
}