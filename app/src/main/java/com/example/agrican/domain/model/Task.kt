package com.example.agrican.domain.model

import java.util.UUID

data class Task(
    val taskName: String = "",
    val taskId: String = UUID.randomUUID().toString()
)
