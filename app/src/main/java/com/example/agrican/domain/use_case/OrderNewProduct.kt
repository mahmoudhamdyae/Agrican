package com.example.agrican.domain.use_case

import com.example.agrican.domain.repository.MainRepository
import javax.inject.Inject

class OrderNewProduct @Inject constructor(
    private val mainRepository: MainRepository
) {

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
        mainRepository.orderNewProduct()
    }
}