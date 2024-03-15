package me.dio.android.urlshortener.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.dio.android.urlshortener.domain.ShortenedUrl
import me.dio.android.urlshortener.domain.UrlsRepository

class UrlsViewModel(
    private val repository: UrlsRepository
) : ViewModel() {
    private val _urls = MutableLiveData<List<ShortenedUrl>>()
    val urls: LiveData<List<ShortenedUrl>> = _urls

    init {
        loadUrls()
    }

    private fun loadUrls() {
        viewModelScope.launch {
            _urls.value = repository.getAll()
        }
    }
}
