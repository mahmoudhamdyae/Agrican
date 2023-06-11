package com.example.agrican.domain.model

data class Order(
    val name: String = "",
    val price: Double = 0.0,
    val description: String = "",
    val t: String = "",
    val orderId: String = ""
)
