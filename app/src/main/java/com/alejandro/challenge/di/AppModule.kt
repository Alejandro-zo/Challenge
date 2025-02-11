package com.alejandro.challenge.di

import com.alejandro.challenge.main.SessionViewModel
import com.alejandro.domain.usecase.appparameters.DeleteAllParameterUseCase
import com.alejandro.domain.usecase.appparameters.GetSessionTimeUseCase
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
        getSessionTimeUseCase: GetSessionTimeUseCase,
        deleteAllParameterUseCase: DeleteAllParameterUseCase,
    ): SessionViewModel =
        SessionViewModel(ioDispatcher, getSessionTimeUseCase, deleteAllParameterUseCase)
}
