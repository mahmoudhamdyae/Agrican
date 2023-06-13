package com.example.agrican.domain.model

import java.util.UUID

data class Pest(
    val title: String = "",
    val name: String = "",
    val categories: List<String> = emptyList(),
    val mainHosts: List<Crop> = emptyList(),
    val lifeCycle: List<String> = emptyList(),
    val damages: List<String> = emptyList(),
    val symptoms: List<String> = emptyList(),
    val injurySeason: String = "",
    val controlTiming: List<String> = emptyList(),
    val pestId: String = UUID.randomUUID().toString()
)
