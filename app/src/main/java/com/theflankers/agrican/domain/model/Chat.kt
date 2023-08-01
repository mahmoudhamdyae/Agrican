package com.theflankers.agrican.domain.model

import java.util.UUID

data class Chat(
    val messages: List<Message> = emptyList(),
    val chatId: String = UUID.randomUUID().toString(),
)
