package com.theflankers.agrican.domain.model.weather

import java.time.LocalDateTime

data class WeatherData(
    val time: LocalDateTime,
    val temperatureCelsius: Double,
    val windSpeed: Double,
    val windGusts: Double,
    val windDirection: Int,
    val weatherType: WeatherType
)