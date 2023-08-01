package com.theflankers.agrican.domain.model.weather

data class WeatherInfo(
    val currentWeatherData: WeatherData?,
    val airQuality: Int
)