package com.theflankers.agrican.domain.model

import android.net.Uri
import java.util.UUID

data class Message(
    val type: MessageType = MessageType.TEXT,
    val userId: String = "",
    val body: String? = null,
    val image: String? = null,
    val audioFile: AudioFile = AudioFile(),
    val messageId: String = UUID.randomUUID().toString(),
)

data class AudioFile(
    val file: Uri = Uri.EMPTY,
    val duration: Int = 0,
    val audioId: String = UUID.randomUUID().toString(),
)

enum class MessageType {
    TEXT, IMAGE, VOICE
}