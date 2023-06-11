package com.example.agrican.ui.screens.home.main.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrican.domain.model.Weather
import com.example.agrican.domain.use_case.BaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val useCase: BaseUseCase
): ViewModel() {

    private var _weather = MutableStateFlow(Weather())
    val weather = _weather.asStateFlow()

    init {
        getWeather()
    }

    private fun getWeather() {
        viewModelScope.launch {
            _weather.value = useCase.getWeatherUseCase()
        }
    }
}