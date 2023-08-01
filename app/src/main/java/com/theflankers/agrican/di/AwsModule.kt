package com.theflankers.agrican.di

import com.amplifyframework.kotlin.auth.KotlinAuthFacade
import com.amplifyframework.kotlin.core.Amplify
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AwsModule {

    @Provides
    fun auth(): KotlinAuthFacade = Amplify.Auth
}