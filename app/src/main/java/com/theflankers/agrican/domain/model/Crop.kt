package com.theflankers.agrican.domain.model

import java.util.UUID

data class Crop(
    val name: String = "",
    val date: String = "",
    val tasks: List<Task> = listOf(),
    val image: String = "",
    val cropId: String = UUID.randomUUID().toString(),
)