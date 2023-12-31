package com.theflankers.agrican.domain.model

import java.util.UUID

data class Order(
    val name: String = "",
    val price: Double = 0.0,
    val currency: String = "EGP",
    val description: String = "",
    val t: String = "",
    val orderId: String = UUID.randomUUID().toString()
)
