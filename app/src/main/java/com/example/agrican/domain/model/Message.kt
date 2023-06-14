package com.example.agrican.domain.model

import java.io.File
import java.util.UUID

data class Message(
    val type: MessageType = MessageType.TEXT,
    val userId: String = "",
    val body: String = "",
    val file: File? = null,
    val messageId: String = UUID.randomUUID().toString(),
)

enum class MessageType {
    TEXT, IMAGE, VOICE
}