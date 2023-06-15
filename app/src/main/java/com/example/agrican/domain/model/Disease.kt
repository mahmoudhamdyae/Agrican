package com.example.agrican.domain.model

import java.util.UUID

data class Disease(
    val title: String = "",
    val name: String = "",
    val description: List<String> = emptyList(),
    val reasons: List<String> = emptyList(),
    val effects: List<String> = emptyList(),
    val cure: List<String> = emptyList(),
    val image: String? = null,
    val diseaseId: String = UUID.randomUUID().toString()
)
