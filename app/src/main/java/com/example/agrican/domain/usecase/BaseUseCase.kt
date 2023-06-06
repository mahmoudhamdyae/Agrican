package com.example.agrican.domain.usecase

import com.example.agrican.domain.usecase.auth.ForgotPassword
import com.example.agrican.domain.usecase.auth.LoginUseCase
import com.example.agrican.domain.usecase.auth.SignOutUseCase
import com.example.agrican.domain.usecase.auth.SignupUseCase
import com.example.agrican.domain.usecase.validation.ValidateEmail
import com.example.agrican.domain.usecase.validation.ValidatePassword
import com.example.agrican.domain.usecase.validation.ValidatePhoneNumber
import com.example.agrican.domain.usecase.validation.ValidateRepeatedPassword
import com.example.agrican.domain.usecase.validation.ValidateUserName

data class BaseUseCase (
    val forgotPassword: ForgotPassword,
    val loginUseCase: LoginUseCase,
    val signOutUseCase: SignOutUseCase,
    val signupUseCase: SignupUseCase,

    val validateEmail: ValidateEmail,
    val validatePassword: ValidatePassword,
    val validatePhoneNumber: ValidatePhoneNumber,
    val validateRepeatedPassword: ValidateRepeatedPassword,
    val validateUserName: ValidateUserName,
)