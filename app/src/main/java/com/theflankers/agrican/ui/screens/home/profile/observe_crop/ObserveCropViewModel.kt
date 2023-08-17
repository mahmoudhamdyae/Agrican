package com.theflankers.agrican.ui.screens.home.profile.observe_crop

import androidx.lifecycle.SavedStateHandle
import com.theflankers.agrican.domain.repository.LogService
import com.theflankers.agrican.domain.use_case.BaseUseCase
import com.theflankers.agrican.ui.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ObserveCropViewModel @Inject constructor(
    useCase: BaseUseCase,
    savedStateHandle: SavedStateHandle,
    logService: LogService
): BaseViewModel(logService) {

    private val _uiState = MutableStateFlow(ObserveCropUiState())
    val uiState = _uiState.asStateFlow()

    init {
//        val cropId: String = checkNotNull(savedStateHandle[ObserveCropDestination.cropIdArg])
//        launchCatching {
//            _uiState.value = _uiState.value.copy(crop = useCase.getCropUseCase(cropId))
//        }
    }
}