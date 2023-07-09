package com.example.agrican.ui.screens.home.main

import com.example.agrican.domain.use_case.BaseUseCase
import com.example.agrican.ui.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: BaseUseCase
): BaseViewModel() {

    private var _uiState = MutableStateFlow(MainUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getNews()
    }

    private fun getNews() {
        launchCatching {
            _uiState.value = _uiState.value.copy(
                news = useCase.getNewsUseCase(isNews = true),
                offers = useCase.getNewsUseCase(isNews = false),
                isNewsLoading = false
            )
        }
    }

    fun getWeather(latitude: Double, longitude: Double) {
        launchCatching {
            _uiState.value = _uiState.value.copy(
                weather = useCase.getWeatherUseCase(latitude, longitude),
                isWeatherLoading = false
            )
        }
    }
}