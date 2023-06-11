package com.example.agrican.ui.screens.home.profile.add_farm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrican.domain.use_case.BaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddFarmViewModel @Inject constructor(
    private val useCase: BaseUseCase
): ViewModel() {

    private var _uiState = MutableStateFlow(AddFarmUiState())
    val uiState = _uiState.asStateFlow()

    fun addFarm() {
        viewModelScope.launch {
            useCase.addFarmUseCase(
                farmName = _uiState.value.farmName,
                farmSize = _uiState.value.farmSize,
                sizeUnit = _uiState.value.sizeUnit,
                day = _uiState.value.day,
                month = _uiState.value.month,
                year = _uiState.value.year,
                cropsType = _uiState.value.cropsType
            )
        }
    }
}