package com.example.agrican.domain.model

import java.util.UUID

data class Farm(
    val name: String = "",
    val image: String = "",
    val farmId: String = UUID.randomUUID().toString()
)
