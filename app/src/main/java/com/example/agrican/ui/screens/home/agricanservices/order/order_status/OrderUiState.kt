package com.example.agrican.ui.screens.home.agricanservices.order.order_status

import com.example.agrican.domain.model.Order

data class OrderUiState(
    val orders: List<Order> = emptyList(),
)
