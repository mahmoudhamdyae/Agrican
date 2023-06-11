package com.example.agrican.domain.model

data class Weather(
    val air: String = "",
    val wind: String = "",
    val windGusts: String = "",
    val degree: Double = 0.0,
    val weatherDescription: String = "",
    val firstInformation: String = "",
    val secondInformation: String = "",
)
