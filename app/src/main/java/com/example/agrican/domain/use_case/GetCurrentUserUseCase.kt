package com.example.agrican.domain.use_case

import com.example.agrican.domain.model.User
import com.example.agrican.domain.model.UserType

class GetCurrentUserUseCase {

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