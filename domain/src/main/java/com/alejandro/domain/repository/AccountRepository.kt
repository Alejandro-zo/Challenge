package com.alejandro.domain.repository

import com.alejandro.domain.entity.Account

interface AccountRepository {

    suspend fun getAccount() : List<Account>

    suspend fun updateAccount() : List<Account>

}
