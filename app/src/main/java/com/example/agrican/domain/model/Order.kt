package com.example.agrican.domain.model

import java.util.UUID

data class Order(
    val name: String = "",
    val price: Double = 0.0,
    val currency: String = "",
    val description: String = "",
    val t: String = "",
    val orderId: String = UUID.randomUUID().toString()
)
