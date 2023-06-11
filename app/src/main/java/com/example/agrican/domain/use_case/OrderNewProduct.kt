package com.example.agrican.domain.use_case

class OrderNewProduct {

    suspend operator fun invoke(
        productType: String,
        productName: String,
        quantity: Int,
        quantityUnit: String,
        receivingAddress: String,
        place: String,
        city: String,
        governorate: String,
        notes: String,
    ) {
    }
}