package me.dio.android.hilt.urlshortener.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.dio.android.hilt.urlshortener.data.net.HideUriService
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataRemoteModule {

    @Provides
    fun provideJsonConverterFactory(): Converter.Factory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRetrofit(converterFactory: Converter.Factory): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://hideuri.com")
            .addConverterFactory(converterFactory)
            .build()

    @Provides
    fun provideHideUriService(retrofit: Retrofit): HideUriService =
        retrofit.create<HideUriService>()

}
