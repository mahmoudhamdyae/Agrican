package com.example.agrican.ui.screens.home.main

import com.example.agrican.domain.model.News
import com.example.agrican.domain.model.Weather

data class MainUiState(
    val weather: Weather = Weather(),
    val news: List<News> = emptyList()
)
