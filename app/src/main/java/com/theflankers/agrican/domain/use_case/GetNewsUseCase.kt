package com.theflankers.agrican.domain.use_case

import com.theflankers.agrican.domain.model.News
import com.theflankers.agrican.domain.repository.MainRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(isNews: Boolean): List<News> {
//        return mainRepository.getNews()

        return if (isNews) {
            listOf(
                News(title = "ابتكار طرق رى جديدة"),
                News(title = "ابتكار طرق رى جديدة"),
                News(title = "ابتكار طرق رى جديدة"),
                News(title = "ابتكار طرق رى جديدة"),
                News(title = "ابتكار طرق رى جديدة"),
                News(title = "ابتكار طرق رى جديدة"),
                News(title = "ابتكار طرق رى جديدة"),
                News(title = "ابتكار طرق رى جديدة")
            )
        } else {
            listOf(
                News(title = "عروض حصرية"),
                News(title = "عروض حصرية"),
                News(title = "عروض حصرية"),
                News(title = "عروض حصرية"),
                News(title = "عروض حصرية"),
                News(title = "عروض حصرية"),
                News(title = "عروض حصرية"),
                News(title = "عروض حصرية"),
            )
        }
    }
}