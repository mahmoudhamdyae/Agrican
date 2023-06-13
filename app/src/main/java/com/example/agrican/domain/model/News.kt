package com.example.agrican.domain.model

import java.util.UUID

data class News(
    val title: String = "",
    val image: String = "",
    val newsId: String = UUID.randomUUID().toString(),
)
