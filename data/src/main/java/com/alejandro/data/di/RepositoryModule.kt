package com.alejandro.data.di

import com.alejandro.data.repository.AccountRepositoryImpl
import com.alejandro.data.repository.AppParameterRepositoryImpl
import com.alejandro.data.repository.AuthRepositoryImpl
import com.alejandro.domain.repository.AccountRepository
import com.alejandro.domain.repository.AppParameterRepository
import com.alejandro.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindAccountRepository(accountRepositoryImpl: AccountRepositoryImpl): AccountRepository

    @Binds
    @Singleton
    abstract fun bindAppParameterRepository(appParameterRepositoryImpl: AppParameterRepositoryImpl): AppParameterRepository
}
