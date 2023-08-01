package com.theflankers.agrican.domain.use_case

import com.theflankers.agrican.common.enums.ProductType
import com.theflankers.agrican.common.enums.Quantity
import com.theflankers.agrican.domain.repository.MainRepository
import javax.inject.Inject

class OrderNewProductUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(
        productType: Int,
        productName: String,
        quantity: Int,
        quantityUnit: Int,
        receivingAddress: String,
        place: String,
        city: String,
        governorate: String,
        notes: String,
    ) {
        val type = when(productType) {
            ProductType.INSECTICIDE.title -> { ProductType.INSECTICIDE.name }
            else -> { ProductType.INSECTICIDE.name }
        }

        val unit = when(quantityUnit) {
            Quantity.KILOGRAM.title -> { Quantity.KILOGRAM.name }
            else -> { Quantity.KILOGRAM.name }
        }

        mainRepository.orderNewProduct()
    }
}