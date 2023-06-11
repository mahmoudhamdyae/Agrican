package com.example.agrican.ui.screens.home.profile.add_crop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrican.domain.use_case.BaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCropViewModel @Inject constructor(
    private val useCase: BaseUseCase
): ViewModel() {

    private var _uiState = MutableStateFlow(AddCropUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value.crops = useCase.getCropsUseCase()
        }
    }

    fun addCrop() {
        viewModelScope.launch {
            useCase.addCropUseCase(
                crop = _uiState.value.selectedCrop,
                day = _uiState.value.day,
                month = _uiState.value.month,
                year = _uiState.value.year
            )
        }
    }
}