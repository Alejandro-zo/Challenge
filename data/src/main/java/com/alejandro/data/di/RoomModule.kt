package com.alejandro.data.di

import android.content.Context
import androidx.room.Room
import com.alejandro.data.local.room.AppDataBase
import com.alejandro.data.local.room.dao.AccountDao
import com.alejandro.data.local.room.dao.AppParameterDao
import com.alejandro.data.local.room.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val DATA_BASE_NAME = "challengeDb"

    @Singleton
    @Provides
    fun providesRoom(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java, DATA_BASE_NAME).build()
    }

    @Singleton
    @Provides
    fun providesAccountDao(db: AppDataBase): AccountDao = db.accountDao()

    @Singleton
    @Provides
    fun providesUserDao(db: AppDataBase): UserDao = db.userDao()

    @Singleton
    @Provides
    fun providesAppParameterDao(db: AppDataBase): AppParameterDao = db.appParameterDao()
}
