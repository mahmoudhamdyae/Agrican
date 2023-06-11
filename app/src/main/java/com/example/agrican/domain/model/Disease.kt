package com.example.agrican.domain.model

data class Disease(
    val title: String = "",
    val name: String = "",
    val description: List<String> = emptyList(),
    val reasons: List<String> = emptyList(),
    val effects: List<String> = emptyList(),
    val cure: List<String> = emptyList(),
    val diseaseId: String = "1234"
)
