package com.example.agrican.domain.use_case

import com.example.agrican.domain.repository.MainRepository
import javax.inject.Inject

class JoinUsUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(
        fullName: String,
        email: String,
        phoneNumber: String,
        image: String?
    ) {

        mainRepository.joinAsExpert(
            fullName = fullName,
            email = email,
            phoneNumber = phoneNumber,
            image = image
        )
    }
}