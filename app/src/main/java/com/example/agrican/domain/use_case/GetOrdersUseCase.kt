package com.example.agrican.domain.use_case

import com.example.agrican.domain.model.Order
import com.example.agrican.domain.repository.MainRepository
import javax.inject.Inject

class GetOrdersUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(): List<Order> {
        return listOf(
            Order(
                name = "سماد عالى الفسفور",
                t = "تقاوي",
                price = 500.0,
                description = "سماد عضوى لتغذية النباتات سماد عضوى لتغذية النباتات سماد عضوى لتغذية النباتات"
            )
        )
    }
}