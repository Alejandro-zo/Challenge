package com.alejandro.data.remote.ktor

import com.alejandro.data.BuildConfig
import com.alejandro.data.local.room.dao.UserDao
import com.alejandro.data.model.request.LoginRequest
import com.alejandro.data.model.response.LoginResponse
import com.alejandro.data.remote.LOGIN
import com.alejandro.data.remote.api.AuthApi
import com.alejandro.data.remote.validate
import com.alejandro.domain.util.Constants.LOGIN_ERROR_MESSAGE
import com.alejandro.domain.util.GenericException
import com.alejandro.domain.util.encryptSHA512
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

class AuthApiImpl @Inject constructor(
    private val ktor: HttpClient,
    private val userDao: UserDao,
) : AuthApi {
    override suspend fun loginDriver(userName: String, password: String) {
        val passwordHash = encryptSHA512(password, BuildConfig.SALT)
        val response = ktor.post(LOGIN) {
            contentType(ContentType.Application.Json)
            setBody(LoginRequest(userName, passwordHash))
        }
        validate<LoginResponse>(response) ?: throw GenericException()
        userDao.getUser(userName, passwordHash) ?: throw GenericException(LOGIN_ERROR_MESSAGE)
    }
}
