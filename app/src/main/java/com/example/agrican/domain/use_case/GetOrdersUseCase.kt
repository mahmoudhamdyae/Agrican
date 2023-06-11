package com.example.agrican.domain.use_case

import com.example.agrican.domain.model.Order

class GetOrdersUseCase {

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