package com.example.agrican.domain.usecase.auth

class SignupUseCase (
) {

    suspend operator fun invoke(
        userName: String,
        password: String,
        phoneNumber: String,
        email: String
    ) {
    }
}