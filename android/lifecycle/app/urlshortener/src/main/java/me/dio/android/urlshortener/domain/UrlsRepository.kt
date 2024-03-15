package me.dio.android.urlshortener.domain

interface UrlsRepository {
    suspend fun getAll() : List<ShortenedUrl>;
}
