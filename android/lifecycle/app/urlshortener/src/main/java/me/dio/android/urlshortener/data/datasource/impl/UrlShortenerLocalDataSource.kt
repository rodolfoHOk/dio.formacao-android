package me.dio.android.urlshortener.data.datasource.impl

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import me.dio.android.urlshortener.data.datasource.UrlShortenerDataSource
import me.dio.android.urlshortener.domain.ShortenedUrl

class UrlShortenerLocalDataSource : UrlShortenerDataSource.Local {
    private val urls = MutableStateFlow(emptyList<ShortenedUrl>())

    override fun getAll(): Flow<List<ShortenedUrl>> = urls.onStart {
        delay(1_500)
    }

    override fun add(shortenedUrl: ShortenedUrl) {
        urls.update { prevValue ->
            val urls = setOf(shortenedUrl) + prevValue.toSet()
            urls.toList()
        }
    }
}

//val urls = listOf(
//    ShortenedUrl("https://www.dio.me", "https://hideuri.com/yQJA11"),
//    ShortenedUrl("https://github.com/digitalinnovationone", "https://hideuri.com/2y657Z"),
//    ShortenedUrl("https://www.linkedin.com/school/dio-makethechange/", "https://hideuri.com/mGZ08N"),
//    ShortenedUrl("https://www.instagram.com/dio_makethechange/", "https://hideuri.com/Dd4g3X"),
//    ShortenedUrl("https://twitter.com/dio_me_", "https://hideuri.com/nOZ62Y"),
//    ShortenedUrl("https://www.facebook.com/diomakethechange/", "https://hideuri.com/v0ZyX1"),
//)
