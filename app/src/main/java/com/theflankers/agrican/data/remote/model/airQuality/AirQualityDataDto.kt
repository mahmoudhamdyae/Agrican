package com.theflankers.agrican.data.remote.model.airQuality

data class AirQualityDataDto(
    val ozone: List<Double>,
    val time: List<String>
)