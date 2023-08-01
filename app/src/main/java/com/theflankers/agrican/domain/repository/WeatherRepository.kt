package com.theflankers.agrican.domain.repository

import com.theflankers.agrican.domain.model.weather.WeatherInfo

interface WeatherRepository {

    suspend fun getWeather(latitude: Double, longitude: Double): WeatherInfo
}