package me.dio.android.hilt.urlshortener.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.room.Room
import me.dio.android.hilt.urlshortener.data.AppDatabase
import me.dio.android.hilt.urlshortener.data.repository.UrlsRepositoryImpl
import me.dio.android.hilt.urlshortener.data.datasource.impl.UrlShortenerLocalDataSource
import me.dio.android.hilt.urlshortener.data.datasource.impl.UrlShortenerRemoteDataSource
import me.dio.android.hilt.urlshortener.data.net.HideUriService
import me.dio.android.hilt.urlshortener.data.net.RetrofitServiceProvider

class ViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        val application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])

        val db = Room.databaseBuilder(application, AppDatabase::class.java, "app.db").build()

        val service = RetrofitServiceProvider.create<HideUriService>()
        val repository = UrlsRepositoryImpl(
            localDataSource = UrlShortenerLocalDataSource(dao = db.shortenedUrlDao()),
            remoteDataSource = UrlShortenerRemoteDataSource(service),
        )
        return UrlsViewModel(repository = repository) as T
    }

}
