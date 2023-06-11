package com.example.agrican.domain.model

data class Crop(
    val name: String = "",
    val date: String = "",
    val tasks: List<Task> = listOf(),
    val image: String = "",
    val cropId: String = "",
)