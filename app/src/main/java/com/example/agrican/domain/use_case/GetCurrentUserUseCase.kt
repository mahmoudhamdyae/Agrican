package com.example.agrican.domain.use_case

import com.example.agrican.domain.model.User
import com.example.agrican.domain.repository.MainRepository
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(): User {
//        return mainRepository.getCurrentUser()

        return User(
            userName = "Test Account",
            phoneNumber = "+86(8)9135210487",
            email = "claudette09@exa.com",
            userId = "1"
        )
    }
}