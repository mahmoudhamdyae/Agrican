package com.example.agrican.domain.model

import java.util.UUID

data class Treatment(
    val name: String = "",
    val description: String = "",
    val treatmentId: String = UUID.randomUUID().toString()
)
