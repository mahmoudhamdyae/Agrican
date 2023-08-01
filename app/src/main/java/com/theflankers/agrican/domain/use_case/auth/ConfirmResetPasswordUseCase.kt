package com.theflankers.agrican.domain.use_case.auth

import com.theflankers.agrican.domain.repository.AccountService
import javax.inject.Inject

class ConfirmResetPasswordUseCase @Inject constructor(
    private val accountService: AccountService
) {

    suspend operator fun invoke(
        userName: String,
        newPassword: String,
        code: String,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        accountService.confirmResetPassword(userName, newPassword, code, onSuccess, onError)
    }
}