package com.example.agrican.domain.use_case

import com.example.agrican.domain.model.News
import com.example.agrican.domain.repository.MainRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(): List<News> {
//        return mainRepository.getNews()

        return listOf(
            News(title = "ابتكار طرق رى جديدة"),
            News(title = "ابتكار طرق رى جديدة"),
            News(title = "ابتكار طرق رى جديدة"),
            News(title = "ابتكار طرق رى جديدة"),
            News(title = "ابتكار طرق رى جديدة"),
            News(title = "ابتكار طرق رى جديدة"),
            News(title = "ابتكار طرق رى جديدة"),
            News(title = "ابتكار طرق رى جديدة")
        )
    }
}