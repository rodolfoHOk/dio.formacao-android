package me.dio.android.hilt.urlshortener.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.dio.android.hilt.urlshortener.data.AppDatabase
import me.dio.android.hilt.urlshortener.data.dao.ShortenedUrlDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataLocalModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "app.db").build()

    @Provides
    fun provideUrlShortenerDao(database: AppDatabase): ShortenedUrlDao =
        database.shortenedUrlDao()

}
