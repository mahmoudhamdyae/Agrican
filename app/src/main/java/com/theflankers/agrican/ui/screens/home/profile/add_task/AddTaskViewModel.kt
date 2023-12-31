package com.theflankers.agrican.ui.screens.home.profile.add_task

import com.theflankers.agrican.domain.repository.LogService
import com.theflankers.agrican.domain.use_case.BaseUseCase
import com.theflankers.agrican.ui.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val useCase: BaseUseCase,
    logService: LogService
): BaseViewModel(logService) {

    private val _uiState = MutableStateFlow(AddTaskUiState())
    val uiState = _uiState.asStateFlow()

    fun flipDay(day: LocalDate) {
        val days = _uiState.value.days.toMutableList()
        if (days.contains(day)) days.remove(day)
        else days.add(day)
        _uiState.value = _uiState.value.copy(days = days)
    }

    fun addTask(taskName: String) {
        launchCatching {
            useCase.addTaskUseCase(taskName = taskName)
        }
    }
}