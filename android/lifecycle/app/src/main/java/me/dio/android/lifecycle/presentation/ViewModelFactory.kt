package me.dio.android.lifecycle.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.dio.android.lifecycle.data.CounterRepositoryImpl
import me.dio.android.lifecycle.presentation.counter.MainViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            MainViewModel::class.java -> MainViewModel(repository = CounterRepositoryImpl()) as T
            SecondViewModel::class.java -> SecondViewModel() as T
            else -> throw IllegalArgumentException("Unknown ViewModel instance for $modelClass")
        }
    }
}
