package com.example.agrican.ui.screens.home.main

import com.example.agrican.domain.model.News
import com.example.agrican.domain.model.weather.WeatherInfo

data class MainUiState(
    val isWeatherLoading: Boolean = true,
    val isNewsLoading: Boolean = true,
    val weather: WeatherInfo? = null,
    val news: List<News> = emptyList(),
    val offers: List<News> = emptyList()
)
