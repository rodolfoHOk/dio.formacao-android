package me.dio.android.urlshortener.domain

import kotlinx.coroutines.flow.Flow

interface UrlsRepository {
    fun getAll() : Flow<List<ShortenedUrl>>
    suspend fun shorten(url: String)
}
