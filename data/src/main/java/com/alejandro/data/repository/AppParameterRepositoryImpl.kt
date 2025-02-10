package com.alejandro.data.repository

import com.alejandro.data.local.room.dao.AppParameterDao
import com.alejandro.data.local.room.entity.AppParameterDb
import com.alejandro.domain.repository.AppParameterRepository
import com.alejandro.domain.util.Constants.EMPTY_STRING
import com.alejandro.domain.util.Constants.SESSION_TIME_PARAMETER
import javax.inject.Inject

class AppParameterRepositoryImpl @Inject constructor(
    private val appDao: AppParameterDao
) : AppParameterRepository {
    override suspend fun saveSessionTime(value: Long) {
        val appParameter = AppParameterDb(key = SESSION_TIME_PARAMETER, value = value.toString())
        appDao.insertAppParameter(appParameter)
    }

    override suspend fun getSessionTime(): Long {
        val value = appDao.getAppParameter(SESSION_TIME_PARAMETER)?.value ?: EMPTY_STRING
        return value.toLongOrNull() ?: 0L
    }

    override suspend fun deleteAllParameter() {
        appDao.deleteAllParameter()
    }
}
