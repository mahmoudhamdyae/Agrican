package com.theflankers.agrican.domain.use_case

import com.theflankers.agrican.domain.model.Order
import com.theflankers.agrican.domain.repository.MainRepository
import javax.inject.Inject

class GetOrderUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(orderId: String): Order {
//        return mainRepository.getOrder(orderId)

        return Order(price = 100.0, currency = "EGP",)
    }
}