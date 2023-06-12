package com.example.agrican.di

import com.example.agrican.data.repository.AccountServiceImpl
import com.example.agrican.data.repository.MainRepositoryImpl
import com.example.agrican.domain.repository.AccountService
import com.example.agrican.domain.repository.MainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

//@Module
//@InstallIn(SingletonComponent::class)
//object RepositoryModule {
//
//    @Provides
//    fun provideAccountService(): AccountService {
//        return AccountServiceImpl()
//    }
//
//    @Provides
//    fun provideMainRepository(): MainRepository {
//        return MainRepositoryImpl()
//    }
//}

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {

    @Binds
    abstract fun provideAccountService(impl: AccountServiceImpl): AccountService

    @Binds
    abstract fun provideMainRepository(impl: MainRepositoryImpl): MainRepository
}