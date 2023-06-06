package com.example.agrican.di

import com.example.agrican.domain.usecase.BaseUseCase
import com.example.agrican.domain.usecase.auth.ForgotPassword
import com.example.agrican.domain.usecase.auth.LoginUseCase
import com.example.agrican.domain.usecase.auth.SignOutUseCase
import com.example.agrican.domain.usecase.auth.SignupUseCase
import com.example.agrican.domain.usecase.validation.ValidateEmail
import com.example.agrican.domain.usecase.validation.ValidatePassword
import com.example.agrican.domain.usecase.validation.ValidatePhoneNumber
import com.example.agrican.domain.usecase.validation.ValidateRepeatedPassword
import com.example.agrican.domain.usecase.validation.ValidateUserName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideUseCase(): BaseUseCase {
        return BaseUseCase(
            forgotPassword = ForgotPassword(),
            loginUseCase = LoginUseCase(),
            signOutUseCase = SignOutUseCase(),
            signupUseCase = SignupUseCase(),

            validateEmail = ValidateEmail(),
            validatePassword = ValidatePassword(),
            validatePhoneNumber = ValidatePhoneNumber(),
            validateRepeatedPassword = ValidateRepeatedPassword(),
            validateUserName = ValidateUserName(),
        )
    }
}