package com.theflankers.agrican.data.mappers

import com.theflankers.agrican.R
import com.theflankers.agrican.data.remote.model.airQuality.AirQualityDto
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.log10

fun AirQualityDto.toAirQualityString(): Int {
    // Get Time
    val now = LocalDateTime.now()
    val time =  airQualityDataDto.time.find {
        val hour = if (now.minute < 30) now.hour else now.hour + 1
        LocalDateTime.parse(it, DateTimeFormatter.ISO_DATE_TIME).hour == hour
    }

    // Get AQI
    val index = airQualityDataDto.time.indexOf(time)
    val ozone = airQualityDataDto.ozone[index]
    val aqi = 100 + 2.5 * log10(ozone / .08)

    // Return the text that will be printed in screen
    return when(aqi.toInt()) {
        in 0..50 -> R.string.air_quality_good
        in 51..100 -> R.string.air_quality_moderate
        in 101..150 -> R.string.air_quality_unhealthy_for_sensitive_groups
        in 151..200 -> R.string.air_quality_unhealthy
        in 201..300  -> R.string.air_quality_very_unhealthy
        in 301..500 -> R.string.air_quality_Hazardous
        else -> R.string.air_quality_good
    }
}