package com.example.agrican.domain.use_case

import com.example.agrican.domain.model.weather.WeatherInfo
import com.example.agrican.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {

    suspend operator fun invoke(latitude: Double, longitude: Double): WeatherInfo {
        return repository.getWeather(latitude, longitude)
    }
}