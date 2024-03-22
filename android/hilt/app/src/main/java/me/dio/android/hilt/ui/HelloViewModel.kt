package me.dio.android.hilt.ui

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class HelloViewModel @Inject constructor() : ViewModel() {
    val text = "Hello from ViewModel"
}
