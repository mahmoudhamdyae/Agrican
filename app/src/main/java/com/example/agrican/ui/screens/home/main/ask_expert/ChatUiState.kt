package com.example.agrican.ui.screens.home.main.ask_expert

import com.example.agrican.domain.model.Chat
import com.example.agrican.domain.model.User

data class ChatUiState(
    val currentUser: User = User(),
    val chat: Chat = Chat(),
)