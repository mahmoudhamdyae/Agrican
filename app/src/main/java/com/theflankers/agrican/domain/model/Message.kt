package com.theflankers.agrican.domain.model

import java.io.File
import java.util.UUID

data class Message(
    val type: MessageType = MessageType.TEXT,
    val userId: String = "",
    val body: String? = null,
    val image: String? = null,
    val audioFile: AudioFile? = null,
    val messageId: String = UUID.randomUUID().toString(),
)

data class AudioFile(
    val file: File? = null,
    val duration: Int = 0
)

enum class MessageType {
    TEXT, IMAGE, VOICE
}