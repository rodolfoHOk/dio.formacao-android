package me.dio.android.lifecycle.presentation.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import me.dio.android.lifecycle.domain.CounterRepository

class MainViewModel(
    private val repository : CounterRepository
) : ViewModel() {
    val counter: LiveData<Int> = repository.getCounter()

    var incrementBy = 1

    fun increment() {
        repository.incrementCounterBy(incrementBy)
    }
}
