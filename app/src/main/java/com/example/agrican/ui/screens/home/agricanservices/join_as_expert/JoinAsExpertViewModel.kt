package com.example.agrican.ui.screens.home.agricanservices.join_as_expert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrican.domain.use_case.BaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JoinAsExpertViewModel @Inject constructor(
    private val useCase: BaseUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(JoinAsExpertUiState())
    val uiState = _uiState.asStateFlow()

    fun send() {
        viewModelScope.launch {
            useCase.joinUsUseCase(
                userName = _uiState.value.userName,
                email = _uiState.value.email,
                phoneNumber = _uiState.value.phoneNumber,
                image = _uiState.value.image
            )
        }
    }
}