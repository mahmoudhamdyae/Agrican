package com.theflankers.agrican.domain.model

import java.io.File
import java.util.UUID

data class Message(
    val type: MessageType = MessageType.TEXT,
    val userId: String = "",
    val body: String? = null,
    val image: String? = null,
    val file: File? = null,
    val messageId: String = UUID.randomUUID().toString(),
)

enum class MessageType {
    TEXT, IMAGE, VOICE
}