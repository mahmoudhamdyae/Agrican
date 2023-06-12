package com.example.agrican.domain.use_case.auth

import com.example.agrican.domain.model.UserType
import com.example.agrican.domain.repository.AccountService
import javax.inject.Inject

class SignupUseCase @Inject constructor(
    private val accountService: AccountService
) {

    suspend operator fun invoke(
        userName: String,
        password: String,
        phoneNumber: String,
        email: String,
        accountType: UserType,
        onSuccess: () -> Unit
    ) {
        accountService.signup(userName, email, password, onSuccess)
    }
}