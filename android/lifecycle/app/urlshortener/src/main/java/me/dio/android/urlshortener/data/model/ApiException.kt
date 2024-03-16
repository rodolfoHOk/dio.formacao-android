package me.dio.android.urlshortener.data.model

import com.google.gson.annotations.SerializedName

data class ApiException(
    @SerializedName("error")
    override val message: String,
) : Exception()
