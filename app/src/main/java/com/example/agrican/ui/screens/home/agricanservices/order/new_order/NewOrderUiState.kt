package com.example.agrican.ui.screens.home.agricanservices.order.new_order

import com.example.agrican.common.enums.ProductType
import com.example.agrican.common.enums.Quantity

data class NewOrderUiState(
    val productType: Int = ProductType.INSECTICIDE.title,
    val quantityUnit: Int = Quantity.KILOGRAM.title,
    val place: String = "",
    val city: String = "",
    val governorate: String = "",
)