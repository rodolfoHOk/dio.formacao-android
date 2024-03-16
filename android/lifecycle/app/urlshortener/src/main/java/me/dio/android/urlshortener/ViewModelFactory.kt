package me.dio.android.urlshortener

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import me.dio.android.urlshortener.data.UrlsRepositoryImpl
import me.dio.android.urlshortener.data.datasource.impl.UrlShortenerLocalDataSource
import me.dio.android.urlshortener.data.datasource.impl.UrlShortenerRemoteDataSource
import me.dio.android.urlshortener.data.net.HideUriService
import me.dio.android.urlshortener.data.net.RetrofitServiceProvider
import me.dio.android.urlshortener.presentation.UrlsViewModel

class ViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        val service = RetrofitServiceProvider.create<HideUriService>()
        val repository = UrlsRepositoryImpl(
            localDataSource = UrlShortenerLocalDataSource(),
            remoteDataSource = UrlShortenerRemoteDataSource(service),
        )
        return UrlsViewModel(repository = repository) as T
    }
}
