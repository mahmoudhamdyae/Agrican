package com.example.agrican.ui.screens.home.main.ask_expert

import android.net.Uri
import android.util.Log
import com.example.agrican.domain.model.Message
import com.example.agrican.domain.model.MessageType
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
            useCase.getChatUseCase().collect {
                _uiState.value = _uiState.value.copy(chat = it)
            }
        }
    }

    fun sendMessage(
        messageBody: String = "",
        image: Uri = Uri.EMPTY,
        messageType: MessageType
    ) {
        val message = Message(
            body = messageBody,
            image = image,
            type = messageType,
        )
        launchCatching {
            useCase.sendMessageUseCase(message)
        }
    }
}