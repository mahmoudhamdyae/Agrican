package com.example.agrican.ui.screens.home.agricanservices.order.new_order

data class NewOrderUiState(
    var productType: String = "",
    var productName: String = "",
    var quantity: Int = 0,
    var quantityUnit: String = "",
    var receivingAddress: String = "",
    var place: String = "",
    var city: String = "",
    var governorate: String = "",
    var notes: String = "",
)