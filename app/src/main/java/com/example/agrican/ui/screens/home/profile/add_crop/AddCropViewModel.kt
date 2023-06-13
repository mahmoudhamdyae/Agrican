package com.example.agrican.ui.screens.home.profile.add_crop

import com.example.agrican.domain.model.Crop
import com.example.agrican.domain.use_case.BaseUseCase
import com.example.agrican.ui.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddCropViewModel @Inject constructor(
    private val useCase: BaseUseCase
): BaseViewModel() {

    private var _uiState = MutableStateFlow(AddCropUiState())
    val uiState = _uiState.asStateFlow()

    init {
        launchCatching {
            _uiState.value = _uiState.value.copy(crops = useCase.getCropsUseCase())
        }
    }

    fun updateUiStates(
        selectedCrop: Crop =  _uiState.value.selectedCrop,
        day: String =  _uiState.value.day,
        month: String =  _uiState.value.month,
        year: String =  _uiState.value.year,
    ) {
        _uiState.value = _uiState.value.copy(
            selectedCrop = selectedCrop,
            day = day,
            month = month,
            year = year,
        )
    }

    fun addCrop() {
        launchCatching {
            useCase.addCropUseCase(
                crop = _uiState.value.selectedCrop,
                day = _uiState.value.day,
                month = _uiState.value.month,
                year = _uiState.value.year
            )
        }
    }
}