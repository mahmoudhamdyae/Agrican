package com.example.agrican.ui.screens.home.main.weather

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
): ViewModel() {

    init {
        getWeather()
    }

    private fun getWeather() {
    }
}