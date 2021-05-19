package com.chithlal.weather.di

import com.chithlal.weather.repository.WeatherApiInterface
import com.chithlal.weather.utlity.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class WeatherModule {

    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttp() = run {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient,baseurl: String): Retrofit =  Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseurl)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiInterface(retrofit: Retrofit) = retrofit.create(WeatherApiInterface::class.java)
}