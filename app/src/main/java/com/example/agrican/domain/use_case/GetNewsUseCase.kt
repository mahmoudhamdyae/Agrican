package com.example.agrican.domain.use_case

import com.example.agrican.domain.model.News

class GetNewsUseCase {

    suspend operator fun invoke(): List<News> {
        return listOf(
            News(title = "ابتكار طرق جديدة"),
            News(title = "ابتكار طرق جديدة"),
            News(title = "ابتكار طرق جديدة"),
            News(title = "ابتكار طرق جديدة"),
            News(title = "ابتكار طرق جديدة"),
        )
    }
}