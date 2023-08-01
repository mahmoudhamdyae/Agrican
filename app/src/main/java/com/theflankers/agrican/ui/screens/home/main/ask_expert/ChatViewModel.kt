package com.theflankers.agrican.ui.screens.home.main.ask_expert

import com.theflankers.agrican.domain.model.Message
import com.theflankers.agrican.domain.model.MessageType
import com.theflankers.agrican.domain.use_case.BaseUseCase
import com.theflankers.agrican.ui.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.File
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
                _uiState.value = _uiState.value.copy(
                    currentUser = useCase.getCurrentUserUseCase(),
                    chat = it
                )
            }
        }
    }

    fun sendMessage(
        messageBody: String? = null,
        image: String? = null,
        file: File? = null,
        messageType: MessageType
    ) {
        val message = Message(
            body = messageBody,
            userId = _uiState.value.currentUser.userId,
            image = image,
            file = file,
            type = messageType,
        )
        launchCatching {
            useCase.sendMessageUseCase(message)
        }
    }
}