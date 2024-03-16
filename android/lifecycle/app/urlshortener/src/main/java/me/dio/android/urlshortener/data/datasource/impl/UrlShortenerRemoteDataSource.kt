package me.dio.android.urlshortener.data.datasource.impl

import com.google.gson.Gson
import me.dio.android.urlshortener.data.datasource.UrlShortenerDataSource
import me.dio.android.urlshortener.data.model.ApiException
import me.dio.android.urlshortener.data.net.HideUriService
import me.dio.android.urlshortener.domain.ShortenedUrl
import retrofit2.HttpException
import java.net.HttpURLConnection

class UrlShortenerRemoteDataSource(
    private val service: HideUriService,
) : UrlShortenerDataSource.Remote {
    override suspend fun create(url: String): ShortenedUrl {
        try {
            val response = service.shorten(url)
            return ShortenedUrl(original = url, url = response.result)
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            if (e.code() == HttpURLConnection.HTTP_BAD_REQUEST && errorBody != null) {
                val error = Gson().fromJson(errorBody, ApiException::class.java)
                throw error
            }
            throw e
        }
    }
}
