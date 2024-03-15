package me.dio.android.urlshortener

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import me.dio.android.urlshortener.data.UrlsRepositoryImpl
import me.dio.android.urlshortener.presentation.UrlsViewModel

class ViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return UrlsViewModel(repository = UrlsRepositoryImpl()) as T
    }
}
