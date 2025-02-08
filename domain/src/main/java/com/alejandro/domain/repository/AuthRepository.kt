package com.alejandro.domain.repository

interface AuthRepository {

    suspend fun login(userName: String, password: String)

    suspend fun saveUser()

}
