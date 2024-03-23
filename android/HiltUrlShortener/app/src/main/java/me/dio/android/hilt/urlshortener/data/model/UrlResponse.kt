package me.dio.android.hilt.urlshortener.data.model

import com.google.gson.annotations.SerializedName

data class UrlResponse(
    @SerializedName("result_url")
    val result: String,
)
