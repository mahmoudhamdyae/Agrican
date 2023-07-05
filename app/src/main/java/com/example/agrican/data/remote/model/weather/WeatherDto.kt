package com.example.agrican.data.remote.model.weather

import com.google.gson.annotations.SerializedName

data class WeatherDto(
    @SerializedName("hourly")
    val weatherData: WeatherDataDto
)