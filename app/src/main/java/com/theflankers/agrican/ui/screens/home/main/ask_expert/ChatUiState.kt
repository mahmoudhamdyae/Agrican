package com.theflankers.agrican.ui.screens.home.main.ask_expert

import android.net.Uri
import com.theflankers.agrican.domain.model.Message
import com.theflankers.agrican.domain.model.User

data class ChatUiState(
    val currentUser: User = User(),
    val messages: List<Message> = emptyList(),
    val isPlaying: Boolean = false,
    val audio: Uri = Uri.EMPTY,
    val currentTime: Int = 0
)