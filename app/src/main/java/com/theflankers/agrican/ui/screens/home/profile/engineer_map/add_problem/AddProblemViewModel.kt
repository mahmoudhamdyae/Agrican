package com.theflankers.agrican.ui.screens.home.profile.engineer_map.add_problem

import com.theflankers.agrican.domain.repository.LogService
import com.theflankers.agrican.domain.use_case.BaseUseCase
import com.theflankers.agrican.ui.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddProblemViewModel @Inject constructor(
    private val useCase: BaseUseCase,
    logService: LogService
): BaseViewModel(logService) {

    private var _uiState = MutableStateFlow(AddProblemUiState())
    val uiState = _uiState.asStateFlow()

    fun updateUiStates(
        problemKind: String =  _uiState.value.problemKind,
        problemDescription: String =  _uiState.value.problemDescription,
        day: Int =  _uiState.value.day,
        month: Int =  _uiState.value.month,
        year: Int =  _uiState.value.year,
    ) {
        _uiState.value = _uiState.value.copy(
            problemKind = problemKind,
            problemDescription = problemDescription,
            day = day,
            month = month,
            year = year,
        )
    }

    fun addProblem() {
        launchCatching {
        }
    }
}