package com.alejandro.data.remote.ktor

import com.alejandro.data.model.response.AccountResponse
import com.alejandro.data.model.response.MovementResponse
import com.alejandro.data.remote.GET_ACCOUNT
import com.alejandro.data.remote.MOVEMENTS
import com.alejandro.data.remote.UPDATE_ACCOUNT
import com.alejandro.data.remote.api.AccountApi
import com.alejandro.data.remote.validate
import com.alejandro.domain.util.GenericException
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import javax.inject.Inject

class AccountApiImpl @Inject constructor(private val ktor: HttpClient) : AccountApi {
    override suspend fun getAccount(): List<AccountResponse> {
        val response = ktor.get(GET_ACCOUNT)
        return validate<List<AccountResponse>>(response) ?: throw GenericException()
    }

    override suspend fun updateAccount(): List<AccountResponse> {
        val response = ktor.get(UPDATE_ACCOUNT)
        return validate<List<AccountResponse>>(response) ?: throw GenericException()
    }

    override suspend fun getMovements(accountNumber: String): List<MovementResponse> {
        val response = ktor.get("$MOVEMENTS/$accountNumber")
        return validate<List<MovementResponse>>(response) ?: throw GenericException()
    }
}
