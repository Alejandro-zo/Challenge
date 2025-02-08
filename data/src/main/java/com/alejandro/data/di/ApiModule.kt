package com.alejandro.data.di

import com.alejandro.data.remote.api.AuthDriverApi
import com.alejandro.data.remote.ktor.AuthDriverApiImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ApiModule {

    @Binds
    @Singleton
    abstract fun bindAuthDriverApi(userDriverApiImpl: AuthDriverApiImpl): AuthDriverApi
}
