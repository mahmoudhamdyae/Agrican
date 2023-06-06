package com.example.agrican.domain.usecase

import com.example.agrican.domain.usecase.validation.ValidateEmail
import com.example.agrican.domain.usecase.validation.ValidatePassword
import com.example.agrican.domain.usecase.validation.ValidateRepeatedPassword
import com.example.agrican.domain.usecase.validation.ValidateUserName

data class BaseUseCase (
    val validateEmail: ValidateEmail,
    val validatePassword: ValidatePassword,
    val validateRepeatedPassword: ValidateRepeatedPassword,
    val validateUserName: ValidateUserName,
)