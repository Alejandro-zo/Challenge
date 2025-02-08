package com.alejandro.data.remote.api

interface AuthDriverApi {

    suspend fun loginDriver(userName: String, password: String)

}
