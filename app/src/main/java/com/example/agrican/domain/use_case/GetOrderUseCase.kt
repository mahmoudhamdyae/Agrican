package com.example.agrican.domain.use_case

import com.example.agrican.domain.model.Order
import com.example.agrican.domain.repository.MainRepository
import javax.inject.Inject

class GetOrderUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(orderId: String): Order {
//        return mainRepository.getOrder(orderId)

        return Order()
    }
}