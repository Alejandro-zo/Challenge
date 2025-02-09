package com.alejandro.data.di

import com.alejandro.data.remote.api.AccountApi
import com.alejandro.data.remote.api.AuthApi
import com.alejandro.data.remote.ktor.AccountApiImpl
import com.alejandro.data.remote.ktor.AuthApiImpl
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
    abstract fun bindAuthApi(authApiImpl: AuthApiImpl): AuthApi

    @Binds
    @Singleton
    abstract fun bindAccountApi(accountApiImpl: AccountApiImpl): AccountApi
}
