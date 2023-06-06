package com.example.agrican.di

import com.example.agrican.domain.usecase.BaseUseCase
import com.example.agrican.domain.usecase.validation.ValidateEmail
import com.example.agrican.domain.usecase.validation.ValidatePassword
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
            validateEmail = ValidateEmail(),
            validatePassword = ValidatePassword(),
            validateRepeatedPassword = ValidateRepeatedPassword(),
            validateUserName = ValidateUserName(),
        )
    }
}