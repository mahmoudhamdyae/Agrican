package com.example.agrican.domain.use_case

import com.example.agrican.domain.model.User
import com.example.agrican.domain.model.UserType
import com.example.agrican.domain.repository.MainRepository
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(): User {
        return User(
            userName = "مستخدم جديد",
            phoneNumber = "",
            email = "",
            userType = UserType.ENGINEER,
            image = "",
            userId = "1"
        )
    }
}