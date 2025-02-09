package com.alejandro.data.remote.api

interface AuthApi {

    suspend fun loginDriver(userName: String, password: String)

}
