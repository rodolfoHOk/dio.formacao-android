package me.dio.android.urlshortener.presentation

sealed interface UrlsAction {
    object Loading : UrlsAction

    object Done : UrlsAction

    data class Failed(val errorMessage: String) : UrlsAction
}
