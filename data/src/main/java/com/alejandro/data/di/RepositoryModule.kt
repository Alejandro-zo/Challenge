package com.alejandro.data.di

import com.alejandro.data.repository.AuthDriverRepositoryImpl
import com.alejandro.domain.repository.AuthDriverRepository
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
    abstract fun bindAuthDriverRepository(authDriverRepositoryImpl: AuthDriverRepositoryImpl): AuthDriverRepository
}
