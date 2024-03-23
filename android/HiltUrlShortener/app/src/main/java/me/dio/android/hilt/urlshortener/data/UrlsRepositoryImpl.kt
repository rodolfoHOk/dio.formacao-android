package me.dio.android.hilt.urlshortener.data

import kotlinx.coroutines.flow.Flow
import me.dio.android.hilt.urlshortener.data.datasource.UrlShortenerDataSource
import me.dio.android.hilt.urlshortener.domain.ShortenedUrl
import me.dio.android.hilt.urlshortener.domain.UrlsRepository

class UrlsRepositoryImpl(
    private val localDataSource: UrlShortenerDataSource.Local,
    private val remoteDataSource: UrlShortenerDataSource.Remote
) : UrlsRepository {
    override fun getAll() : Flow<List<ShortenedUrl>> = localDataSource.getAll()

    override suspend fun shorten(url: String) {
        val response = remoteDataSource.create(url)
        localDataSource.add(response)
    }
}
