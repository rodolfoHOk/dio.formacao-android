package me.dio.android.lifecycle.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
//    private val _counter = MutableLiveData(0) // normal
    private val _counter = NumberLiveData(0) // life cycle test
    val counter: LiveData<Int> = _counter

    fun increment() {
        val number = _counter.value ?: 0
        _counter.value = number + 1
    }
}

class NumberLiveData(initial: Int = 0) : MutableLiveData<Int>(initial) {
    override fun onActive() {
        super.onActive()
        Log.d("MainViewModel", "onActive")
    }

    override fun onInactive() {
        super.onInactive()
        Log.d("MainViewModel", "onInactive")
    }
}
