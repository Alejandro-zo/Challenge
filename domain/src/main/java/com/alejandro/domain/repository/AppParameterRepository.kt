package com.alejandro.domain.repository

interface AppParameterRepository {

    suspend fun saveSessionTime(value: Long)

    suspend fun getSessionTime(): Long

    suspend fun deleteAllParameter()
}
