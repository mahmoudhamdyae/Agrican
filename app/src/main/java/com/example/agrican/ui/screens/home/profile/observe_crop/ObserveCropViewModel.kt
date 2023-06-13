package com.example.agrican.ui.screens.home.profile.observe_crop

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrican.domain.use_case.BaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ObserveCropViewModel @Inject constructor(
    useCase: BaseUseCase,
    savedStateHandle: SavedStateHandle,
): ViewModel() {

    private val _uiState = MutableStateFlow(ObserveCropUiState())
    val uiState = _uiState.asStateFlow()

    init {
        val cropId: String = checkNotNull(savedStateHandle[ObserveCropDestination.cropIdArg])
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(crop = useCase.getCropUseCase(cropId))
        }
    }
}