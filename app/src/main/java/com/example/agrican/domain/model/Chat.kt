package com.example.agrican.domain.model

data class Chat(
    val messages: List<Message> = emptyList(),
    val chatId: String = "",
)
