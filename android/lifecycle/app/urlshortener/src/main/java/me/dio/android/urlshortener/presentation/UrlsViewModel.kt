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
    private val _state = MutableLiveData<UrlsState>()
    val state: LiveData<UrlsState> = _state

    init {
        loadUrls()
    }

    private fun loadUrls() {
        _state.value = UrlsState.Loading
        viewModelScope.launch {
            _state.value = UrlsState.Success(repository.getAll())
        }
    }
}

sealed interface UrlsState {
    val urls: List<ShortenedUrl>
        get() = listOf()
    val isProgressVisible: Boolean
        get() = false

    data class Success(override val urls: List<ShortenedUrl>) : UrlsState

    object Loading : UrlsState {
        override val isProgressVisible: Boolean = true
    }
}
