package com.example.agrican.domain.use_case

import com.example.agrican.domain.model.News
import com.example.agrican.domain.repository.MainRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(): List<News> {
//        return mainRepository.getNews()

        delay(5000)

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