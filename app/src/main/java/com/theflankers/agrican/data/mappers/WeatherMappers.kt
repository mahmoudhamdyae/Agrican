package com.theflankers.agrican.data.mappers

import com.theflankers.agrican.data.remote.model.weather.WeatherDataDto
import com.theflankers.agrican.data.remote.model.weather.WeatherDto
import com.theflankers.agrican.domain.model.weather.WeatherData
import com.theflankers.agrican.domain.model.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)

fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val windGusts = windGusts[index]
        val windDirection = windDirection[index]
        IndexedWeatherData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                windSpeed = windSpeed,
                windGusts = windGusts,
                windDirection = WeatherType.getWindDirection(windDirection),
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map { it.data }
    }
}

fun WeatherDto.toCurrentWeatherData(): WeatherData? {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    return weatherDataMap[0]?.find {
        val hour = if (now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }
}