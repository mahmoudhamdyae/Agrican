package com.theflankers.agrican.data.repository

import com.theflankers.agrican.data.mappers.toAirQualityString
import com.theflankers.agrican.data.mappers.toCurrentWeatherData
import com.theflankers.agrican.data.remote.AirQualityApiService
import com.theflankers.agrican.data.remote.WeatherApiService
import com.theflankers.agrican.domain.model.weather.WeatherInfo
import com.theflankers.agrican.domain.repository.WeatherRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApiService: WeatherApiService,
    private val airQualityApiService: AirQualityApiService
): WeatherRepository {

    @OptIn(DelicateCoroutinesApi::class)
    override suspend fun getWeather(latitude: Double, longitude: Double): WeatherInfo {
        val airQuality = GlobalScope.async {
            airQualityApiService.getAirQuality(latitude, longitude).toAirQualityString()
        }
        val currentWeatherData = GlobalScope.async {
            weatherApiService.getWeather(latitude, longitude).toCurrentWeatherData()
        }
        return WeatherInfo(currentWeatherData.await(), airQuality.await())
    }
}