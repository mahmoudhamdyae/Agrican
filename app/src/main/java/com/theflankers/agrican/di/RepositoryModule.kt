package com.theflankers.agrican.di

import com.theflankers.agrican.data.repository.AccountServiceImpl
import com.theflankers.agrican.data.repository.MainRepositoryImpl
import com.theflankers.agrican.data.repository.WeatherRepositoryImpl
import com.theflankers.agrican.domain.repository.AccountService
import com.theflankers.agrican.domain.repository.MainRepository
import com.theflankers.agrican.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideAccountService(impl: AccountServiceImpl): AccountService

    @Binds
    abstract fun provideMainRepository(impl: MainRepositoryImpl): MainRepository

    @Binds
    abstract fun provideWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository
}