package com.alejandro.domain.repository

interface AuthDriverRepository {

    suspend fun login(userName: String, password: String)

    suspend fun saveUser()

}
