package com.example.agrican.domain.use_case.auth

import com.example.agrican.domain.repository.AccountService
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val accountService: AccountService
) {

    suspend operator fun invoke(
        userName: String,
        password: String,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        accountService.login(userName, password, onSuccess, onError)
    }
}