package com.theflankers.agrican.ui.screens.home.main.ask_expert

import com.theflankers.agrican.domain.model.AudioFile
import com.theflankers.agrican.domain.model.Message
import com.theflankers.agrican.domain.model.User

data class ChatUiState(
    val currentUser: User = User(),
    val messages: List<Message> = emptyList(),
    val selectedAudio: AudioFile? = null,
    val isPlaying: Boolean = false,
    val currentTime: Int = 0
)