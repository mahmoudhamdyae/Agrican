package com.example.agrican.ui.screens.home.agricanservices.order.confirm_order

import com.example.agrican.domain.model.Order
import com.example.agrican.domain.model.User

data class OrderConfirmUiState(
    val order: Order = Order(),
    val currentUser: User = User()
)