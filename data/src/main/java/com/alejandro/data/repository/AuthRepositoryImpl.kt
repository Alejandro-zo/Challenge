package com.alejandro.data.repository

import com.alejandro.data.BuildConfig
import com.alejandro.data.local.room.dao.UserDao
import com.alejandro.data.local.room.entity.UserDb
import com.alejandro.data.remote.api.AuthApi
import com.alejandro.domain.repository.AuthRepository
import com.alejandro.domain.util.Constants.USER_1
import com.alejandro.domain.util.Constants.USER_2
import com.alejandro.domain.util.Constants.USER_3
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val authApi: AuthApi,
    private val userDao: UserDao,
) : AuthRepository {
    override suspend fun login(userName: String, password: String) = withContext(ioDispatcher) {
        authApi.loginDriver(userName, password)
    }

    override suspend fun saveUser() = withContext(ioDispatcher) {
        userDao.insertUser(UserDb(USER_1, BuildConfig.PASSWORD_USER_1))
        userDao.insertUser(UserDb(USER_2, BuildConfig.PASSWORD_USER_2))
        userDao.insertUser(UserDb(USER_3, BuildConfig.PASSWORD_USER_3))
    }
}
