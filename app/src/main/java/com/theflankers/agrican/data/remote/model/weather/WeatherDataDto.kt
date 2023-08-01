package com.theflankers.agrican.data.remote.model.weather

import com.google.gson.annotations.SerializedName

data class WeatherDataDto(
    val time: List<String>,
    @SerializedName("temperature_2m")
    val temperatures: List<Double>,
    @SerializedName("weathercode")
    val weatherCodes: List<Int>,
    @SerializedName("windspeed_10m")
    val windSpeeds: List<Double>,
    @SerializedName("windgusts_10m")
    val windGusts: List<Double>,
    @SerializedName("winddirection_10m")
    val windDirection: List<Double>,
)