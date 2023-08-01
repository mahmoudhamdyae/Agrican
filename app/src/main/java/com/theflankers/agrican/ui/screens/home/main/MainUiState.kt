package com.theflankers.agrican.ui.screens.home.main

import com.theflankers.agrican.domain.model.News
import com.theflankers.agrican.domain.model.weather.WeatherInfo

data class MainUiState(
    val isWeatherLoading: Boolean = true,
    val isNewsLoading: Boolean = true,
    val weather: WeatherInfo? = null,
    val news: List<News> = emptyList(),
    val offers: List<News> = emptyList()
)
