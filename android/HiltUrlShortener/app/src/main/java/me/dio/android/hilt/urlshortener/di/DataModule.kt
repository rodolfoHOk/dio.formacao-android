package me.dio.android.hilt.urlshortener.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.dio.android.hilt.urlshortener.data.datasource.UrlShortenerDataSource
import me.dio.android.hilt.urlshortener.data.datasource.impl.UrlShortenerLocalDataSource
import me.dio.android.hilt.urlshortener.data.datasource.impl.UrlShortenerRemoteDataSource
import me.dio.android.hilt.urlshortener.data.repository.UrlsRepositoryImpl
import me.dio.android.hilt.urlshortener.domain.UrlsRepository

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun provideUrlsRepository(repository: UrlsRepositoryImpl): UrlsRepository

    @Binds
    fun provideUrlShortenerDataSourceLocal(
        localDataSource: UrlShortenerLocalDataSource
    ): UrlShortenerDataSource.Local

    @Binds
    fun provideUrlShortenerDataSourceRemote(
        remoteDataSource: UrlShortenerRemoteDataSource
    ): UrlShortenerDataSource.Remote

}
