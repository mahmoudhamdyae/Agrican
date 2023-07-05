package com.example.agrican.domain.repository

import com.example.agrican.domain.model.weather.WeatherInfo

interface WeatherRepository {

    suspend fun getWeather(latitude: Double, longitude: Double): WeatherInfo
}