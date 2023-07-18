package com.example.agrican.di

import com.example.agrican.common.utils.Constant.Air_Quality_BASE_URL
import com.example.agrican.common.utils.Constant.BASE_URL
import com.example.agrican.common.utils.Constant.PAYMOB_BASE_URL
import com.example.agrican.common.utils.Constant.WEATHER_BASE_URL
import com.example.agrican.data.remote.AirQualityApiService
import com.example.agrican.data.remote.ApiService
import com.example.agrican.data.remote.PaymobApiService
import com.example.agrican.data.remote.WeatherApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(okHttpClient: OkHttpClient): ApiService {
        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherApiService(okHttpClient: OkHttpClient): WeatherApiService {
        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(WEATHER_BASE_URL)
            .build()
        return retrofit.create(WeatherApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAirQualityApiService(okHttpClient: OkHttpClient): AirQualityApiService {
        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Air_Quality_BASE_URL)
            .build()
        return retrofit.create(AirQualityApiService::class.java)
    }

    @Provides
    @Singleton
    fun providePaymobApiService(okHttpClient: OkHttpClient): PaymobApiService {
        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(PAYMOB_BASE_URL)
            .build()
        return retrofit.create(PaymobApiService::class.java)
    }
}