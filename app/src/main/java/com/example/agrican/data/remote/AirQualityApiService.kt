package com.example.agrican.data.remote

import com.example.agrican.data.remote.model.airQuality.AirQualityDto
import retrofit2.http.GET
import retrofit2.http.Query

interface AirQualityApiService {

    @GET("v1/air-quality?hourly=ozone")
    suspend fun getAirQuality(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double
    ): AirQualityDto
}