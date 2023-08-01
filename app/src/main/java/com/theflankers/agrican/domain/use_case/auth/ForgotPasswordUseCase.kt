package com.theflankers.agrican.domain.use_case.auth

import com.theflankers.agrican.domain.repository.AccountService
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(
    private val accountService: AccountService
) {

    suspend operator fun invoke(userName: String, onSuccess: () -> Unit, onError: () -> Unit) {
        accountService.resetPassword(userName, onSuccess, onError)
    }
}