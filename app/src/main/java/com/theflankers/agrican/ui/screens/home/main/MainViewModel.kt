package com.theflankers.agrican.ui.screens.home.main

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.theflankers.agrican.domain.use_case.BaseUseCase
import com.theflankers.agrican.ui.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
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
        viewModelScope.launch {
            Log.d("hahahaha", useCase.getWeatherUseCase(latitude, longitude).toString())
        }

        launchCatching {
            _uiState.value = _uiState.value.copy(
                weather = useCase.getWeatherUseCase(latitude, longitude),
                isWeatherLoading = false
            )
        }
    }
}