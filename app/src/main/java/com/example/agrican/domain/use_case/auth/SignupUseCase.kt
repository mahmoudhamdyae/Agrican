package com.example.agrican.domain.use_case.auth

import com.example.agrican.domain.model.UserType

class SignupUseCase (
) {

    suspend operator fun invoke(
        userName: String,
        password: String,
        phoneNumber: String,
        email: String,
        accountType: UserType,
    ) {
    }
}