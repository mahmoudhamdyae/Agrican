package com.example.agrican.di

import com.example.agrican.data.repository.AccountServiceImpl
import com.example.agrican.data.repository.MainRepositoryImpl
import com.example.agrican.domain.repository.AccountService
import com.example.agrican.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideAccountService(): AccountService {
        return AccountServiceImpl()
    }

    @Provides
    fun provideMainRepository(): MainRepository {
        return MainRepositoryImpl()
    }
}