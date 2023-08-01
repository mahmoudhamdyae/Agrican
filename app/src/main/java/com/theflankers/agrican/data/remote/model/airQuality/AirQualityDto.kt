package com.theflankers.agrican.data.remote.model.airQuality

import com.google.gson.annotations.SerializedName

data class AirQualityDto(
    @SerializedName("hourly")
    val airQualityDataDto: AirQualityDataDto
)