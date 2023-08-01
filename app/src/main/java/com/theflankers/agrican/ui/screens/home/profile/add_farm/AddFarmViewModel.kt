package com.theflankers.agrican.ui.screens.home.profile.add_farm

import com.theflankers.agrican.domain.use_case.BaseUseCase
import com.theflankers.agrican.ui.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddFarmViewModel @Inject constructor(
    private val useCase: BaseUseCase
): BaseViewModel() {

    private var _uiState = MutableStateFlow(AddFarmUiState())
    val uiState = _uiState.asStateFlow()
    
    fun updateUiStates(
        farmName: String =  _uiState.value.farmName,
        farmSize: String =  _uiState.value.farmSize,
        sizeUnit: Int =  _uiState.value.sizeUnit,
        cropsType: String =  _uiState.value.cropsType,
        day: Int =  _uiState.value.day,
        month: Int =  _uiState.value.month,
        year: Int =  _uiState.value.year,
    ) {
        _uiState.value = _uiState.value.copy(
            farmName = farmName,
            farmSize = farmSize,
            sizeUnit = sizeUnit,
            cropsType = cropsType,
            day = day,
            month = month,
            year = year,
        )
    }

    fun addFarm() {
        launchCatching {
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