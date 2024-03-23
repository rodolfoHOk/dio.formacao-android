package me.dio.android.hilt.urlshortener.data.datasource

import kotlinx.coroutines.flow.Flow
import me.dio.android.hilt.urlshortener.domain.ShortenedUrl

interface UrlShortenerDataSource {
    interface Local {
        fun getAll() : Flow<List<ShortenedUrl>>
        suspend fun add(shortenedUrl: ShortenedUrl)
    }

    interface Remote : UrlShortenerDataSource {
        suspend fun create(url: String) : ShortenedUrl
    }
}
