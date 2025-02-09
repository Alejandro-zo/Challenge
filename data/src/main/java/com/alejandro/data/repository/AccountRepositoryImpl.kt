package com.alejandro.data.repository

import com.alejandro.data.local.room.dao.AccountDao
import com.alejandro.data.local.room.entity.toDomain
import com.alejandro.data.local.room.entity.totoDataBase
import com.alejandro.data.model.response.toDomain
import com.alejandro.data.remote.api.AccountApi
import com.alejandro.domain.entity.Account
import com.alejandro.domain.entity.Movement
import com.alejandro.domain.repository.AccountRepository
import com.alejandro.domain.util.GenericException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val accountApi: AccountApi,
    private val accountDao: AccountDao
) : AccountRepository {
    override suspend fun getAccount(): List<Account> = withContext(ioDispatcher) {
        val result = accountApi.getAccount()
        val listAccount = result.toDomain()
        insetAccount(listAccount)
        listAccount
    }

    override suspend fun updateAccount(): List<Account> = withContext(ioDispatcher) {
        val result = accountApi.updateAccount()
        val listAccount = result.toDomain()
        insetAccount(listAccount)
        listAccount
    }

    override suspend fun getAccountByAccountNumber(
        accountNumber: String
    ): Account = withContext(ioDispatcher) {
        accountDao.getAccountByAccountNumber(accountNumber)?.toDomain() ?: throw GenericException()
    }

    override suspend fun getMovements(
        accountNumber: String
    ): List<Movement> = withContext(ioDispatcher) {
        val result = accountApi.getMovements(accountNumber)
        result.toDomain()
    }

    private suspend fun insetAccount(account: List<Account>) = withContext(ioDispatcher) {
        account.map {
            accountDao.insertAccount(it.totoDataBase())
        }
    }
}
