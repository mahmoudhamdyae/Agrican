package com.example.agrican.domain.use_case.auth

import com.example.agrican.domain.repository.AccountService
import javax.inject.Inject

class ConfirmResetPasswordUseCase @Inject constructor(
    private val accountService: AccountService
) {

    suspend operator fun invoke(userName: String, newPassword: String, code: String, onSuccess: () -> Unit) {
        accountService.confirmResetPassword(userName, newPassword, code, onSuccess)
    }
}