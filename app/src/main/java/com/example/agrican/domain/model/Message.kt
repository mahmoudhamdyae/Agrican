package com.example.agrican.domain.model

data class Message(
    val type: MessageType = MessageType.TEXT,
    val body: String = "",
    val userId: String = "",
    val messageId: String = "",
)

enum class MessageType {
    TEXT, IMAGE, VOICE, DOCUMENT
}