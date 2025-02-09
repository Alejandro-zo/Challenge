package com.alejandro.data.remote.api

import com.alejandro.data.model.response.AccountResponse
import com.alejandro.data.model.response.MovementResponse

interface AccountApi {

    suspend fun getAccount() : List<AccountResponse>

    suspend fun updateAccount() : List<AccountResponse>

    suspend fun getMovements(accountNumber: String) : List<MovementResponse>
}
