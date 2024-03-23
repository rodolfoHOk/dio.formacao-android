package me.dio.android.hilt.urlshortener.data.datasource.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import me.dio.android.hilt.urlshortener.data.dao.ShortenedUrlDao
import me.dio.android.hilt.urlshortener.data.datasource.UrlShortenerDataSource
import me.dio.android.hilt.urlshortener.data.model.ShortenedUrlEntity
import me.dio.android.hilt.urlshortener.domain.ShortenedUrl
import javax.inject.Inject

class UrlShortenerLocalDataSource @Inject constructor(
    private val dao: ShortenedUrlDao
) : UrlShortenerDataSource.Local {

    override fun getAll(): Flow<List<ShortenedUrl>> = dao.getAll().map { list ->
        list.map { shortenedUrlEntity ->  
            ShortenedUrl(original = shortenedUrlEntity.original, url = shortenedUrlEntity.url)
        }
    }

    override suspend fun add(shortenedUrl: ShortenedUrl) = withContext(Dispatchers.IO) {
        val entity = ShortenedUrlEntity(original = shortenedUrl.original, url = shortenedUrl.url)
        dao.insert(entity)
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
