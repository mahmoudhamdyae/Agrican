package com.example.agrican.ui.screens.home.agricanservices.order.order_status

import com.example.agrican.domain.model.Order
import com.example.agrican.domain.model.User

data class OrderUiState(
    val isLoading: Boolean = true,
    val token: String? = null,
    val currentUser: User = User(),
    val orders: List<Order>? = null,
)
