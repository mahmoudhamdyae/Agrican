package com.example.agrican.ui.screens.home.main

import com.example.agrican.domain.model.News
import com.example.agrican.domain.model.Weather

data class MainUiState(
    var weather: Weather = Weather(),
    var news: List<News> = emptyList()
)
