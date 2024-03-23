package me.dio.android.hilt.urlshortener.data.model

import com.google.gson.annotations.SerializedName

data class ApiException(
    @SerializedName("error")
    override val message: String,
) : Exception()
