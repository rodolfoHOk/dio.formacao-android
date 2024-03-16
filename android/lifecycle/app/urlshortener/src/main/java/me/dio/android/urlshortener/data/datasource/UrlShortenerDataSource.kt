package me.dio.android.urlshortener.data.datasource

import kotlinx.coroutines.flow.Flow
import me.dio.android.urlshortener.domain.ShortenedUrl

interface UrlShortenerDataSource {
    interface Local {
        fun getAll() : Flow<List<ShortenedUrl>>
        fun add(shortenedUrl: ShortenedUrl)
    }

    interface Remote : UrlShortenerDataSource {
        suspend fun create(url: String) : ShortenedUrl
    }
}
