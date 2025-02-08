package com.alejandro.data.di

import com.alejandro.data.repository.AuthRepositoryImpl
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
    abstract fun bindAuthRepository(authDriverRepositoryImpl: AuthRepositoryImpl): AuthRepository
}
