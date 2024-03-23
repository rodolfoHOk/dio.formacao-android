package me.dio.android.hilt.urlshortener.data

import androidx.room.Database
import androidx.room.RoomDatabase
import me.dio.android.hilt.urlshortener.data.dao.ShortenedUrlDao
import me.dio.android.hilt.urlshortener.data.model.ShortenedUrlEntity

@Database(entities = [ShortenedUrlEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun shortenedUrlDao(): ShortenedUrlDao

}
