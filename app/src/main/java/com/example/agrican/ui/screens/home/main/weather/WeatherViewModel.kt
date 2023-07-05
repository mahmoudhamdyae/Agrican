package com.example.agrican.ui.screens.home.main.weather

import com.example.agrican.domain.model.weather.WeatherInfo
import com.example.agrican.domain.use_case.BaseUseCase
import com.example.agrican.ui.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val useCase: BaseUseCase
): BaseViewModel() {

    private var _weather = MutableStateFlow<WeatherInfo?>(null)
    val weather = _weather.asStateFlow()

    init {
        getWeather()
    }

    private fun getWeather() {
        launchCatching {
            _weather.value = useCase.getWeatherUseCase()
        }
    }
}