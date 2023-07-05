package com.example.agrican.data.repository

import com.example.agrican.data.mappers.toWeatherInfo
import com.example.agrican.data.remote.WeatherApiService
import com.example.agrican.domain.model.weather.WeatherInfo
import com.example.agrican.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiService: WeatherApiService
): WeatherRepository {

    override suspend fun getWeather(latitude: Double, longitude: Double): WeatherInfo {
        return apiService.getWeather(latitude, longitude).toWeatherInfo()
    }
}