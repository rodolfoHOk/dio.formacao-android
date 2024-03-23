package me.dio.android.hilt.urlshortener.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "urls")
data class ShortenedUrlEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "original_url") val original: String,
    @ColumnInfo(name = "shortened_url") val url: String,
)
