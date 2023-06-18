package com.example.agrican.domain.use_case.auth

import com.example.agrican.domain.repository.AccountService
import javax.inject.Inject

class ConfirmSignUpUseCase @Inject constructor(
    private val accountService: AccountService
) {

    suspend operator fun invoke(userName: String, code: String, password: String, onSuccess: () -> Unit) {
        accountService.confirmSignUp(userName, code, password, onSuccess)
    }
}