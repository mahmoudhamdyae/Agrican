package com.theflankers.agrican.ui.screens.home.main.ask_expert

import android.net.Uri
import com.theflankers.agrican.domain.model.Chat
import com.theflankers.agrican.domain.model.User

data class ChatUiState(
    val currentUser: User = User(),
    val chat: Chat = Chat(),
    val isPlaying: Boolean = false,
    val audio: Uri = Uri.EMPTY,
    val currentTime: Int = 0
)