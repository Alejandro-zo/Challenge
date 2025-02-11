package com.alejandro.domain.repository

import com.alejandro.domain.entity.Account
import com.alejandro.domain.entity.Movement

interface AccountRepository {

    suspend fun getAccount() : List<Account>

    suspend fun updateAccount() : List<Account>

    suspend fun saveAccount(account: Account)

    suspend fun getAccountByAccountNumber(accountNumber: String) : Account

    suspend fun getMovements(accountNumber: String) : List<Movement>

}
