package com.alejandro.data.remote.api

import com.alejandro.data.model.response.AccountResponse

interface AccountApi {

    suspend fun getAccount() : List<AccountResponse>

    suspend fun updateAccount() : List<AccountResponse>
}
