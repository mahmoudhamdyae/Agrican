package com.example.agrican.ui.screens.auth

sealed class AuthFormEvent {
    data class UserNameChanged(val userName: String) : AuthFormEvent()
    data class PasswordChanged(val password: String) : AuthFormEvent()
    data class RepeatedPasswordChanged(
        val repeatedPassword: String
    ) : AuthFormEvent()
    data class PhoneNumberChanged(val phoneNumber: String) : AuthFormEvent()
    data class EmailChanged(val email: String) : AuthFormEvent()
    data class ConfirmCodeChanged(val code: String) : AuthFormEvent()

    object Submit: AuthFormEvent()
    object ConfirmSignUp: AuthFormEvent()
    object ForgotPassword: AuthFormEvent()
}
