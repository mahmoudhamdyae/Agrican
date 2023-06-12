package com.example.agrican.ui.screens.home.main.ask_expert

import com.example.agrican.domain.use_case.BaseUseCase
import com.example.agrican.ui.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val useCase: BaseUseCase
): BaseViewModel() {

    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState = _uiState.asStateFlow()

    init {
        launchCatching {
            _uiState.value.chat = useCase.getChatUseCase()
        }
    }

    fun sendMessage(messageBody: String) {
        launchCatching {
            useCase.sendMessageUseCase(messageBody)
        }
    }
}