package com.example.agrican.di

import com.amplifyframework.auth.AuthCategory
import com.amplifyframework.core.Amplify
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AwsModule {

    @Provides
    fun auth(): AuthCategory = Amplify.Auth
}