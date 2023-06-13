package com.example.agrican.domain.model

import java.util.UUID

data class Message(
    val type: MessageType = MessageType.TEXT,
    val body: String = "",
    val userId: String = "",
    val messageId: String = UUID.randomUUID().toString(),
)

enum class MessageType {
    TEXT, IMAGE, VOICE, DOCUMENT
}