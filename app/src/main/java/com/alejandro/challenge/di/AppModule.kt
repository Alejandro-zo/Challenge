package com.alejandro.challenge.di

import com.alejandro.challenge.main.SessionViewModel
import com.alejandro.domain.repository.AppParameterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Singleton
    @Provides
    fun providesInactivityViewModel(
        ioDispatcher: CoroutineDispatcher,
        appParameterRepository: AppParameterRepository,
    ): SessionViewModel = SessionViewModel(ioDispatcher, appParameterRepository)
}
