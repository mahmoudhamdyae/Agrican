package com.theflankers.agrican.ui.screens.home.profile.engineer_map.add_progress

import com.theflankers.agrican.domain.use_case.BaseUseCase
import com.theflankers.agrican.ui.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddProgressViewModel @Inject constructor(
    private val useCase: BaseUseCase
): BaseViewModel() {

    private var _uiState = MutableStateFlow(AddProgressUiState())
    val uiState = _uiState.asStateFlow()

    fun updateUiStates(
        progress: String =  _uiState.value.progress,
        day: Int =  _uiState.value.day,
        month: Int =  _uiState.value.month,
        year: Int =  _uiState.value.year,
    ) {
        _uiState.value = _uiState.value.copy(
            progress = progress,
            day = day,
            month = month,
            year = year,
        )
    }

    fun addProgress() {
        launchCatching {
        }
    }
}