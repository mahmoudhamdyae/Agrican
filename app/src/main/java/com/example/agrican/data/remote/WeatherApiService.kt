package com.example.agrican.data.remote

import com.example.agrican.data.remote.model.weather.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("v1/forecast?hourly=temperature_2m,weathercode,windspeed_10m,winddirection_10m,windgusts_10m")
    suspend fun getWeather(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double
    ): WeatherDto
}