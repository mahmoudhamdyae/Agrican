package com.example.agrican.domain.model

import android.net.Uri
import java.util.UUID

data class Message(
    val type: MessageType = MessageType.TEXT,
    val body: String = "",
    val image: Uri = Uri.EMPTY,
    val userId: String = "",
    val messageId: String = UUID.randomUUID().toString(),
)

enum class MessageType {
    TEXT, IMAGE, VOICE, DOCUMENT
}