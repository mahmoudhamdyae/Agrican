package com.example.agrican.domain.use_case.auth

import com.example.agrican.domain.repository.AccountService
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(
    private val accountService: AccountService
) {

    suspend operator fun invoke() {
//        accountService.forgotPassword()
    }
}