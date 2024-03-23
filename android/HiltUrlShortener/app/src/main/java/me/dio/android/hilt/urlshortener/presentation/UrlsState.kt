package me.dio.android.hilt.urlshortener.presentation

import me.dio.android.hilt.urlshortener.domain.ShortenedUrl

sealed interface UrlsState {
    val urls: List<ShortenedUrl>
        get() = listOf()
    val isProgressVisible: Boolean
        get() = false
    val errorMessage : String?
        get() = null
    val isErrorMessageVisible: Boolean
        get() = errorMessage != null

    data class Success(override val urls: List<ShortenedUrl>) : UrlsState

    data class Failed(override val errorMessage: String) : UrlsState

    object Loading : UrlsState {
        override val isProgressVisible: Boolean = true
    }

    object Empty : UrlsState {
        override val errorMessage: String = "Nenhuma URL encontrada"
    }
}
