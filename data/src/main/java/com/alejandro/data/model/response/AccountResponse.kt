package com.alejandro.data.model.response

import com.alejandro.domain.entity.Account
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class AccountResponse(
    @SerialName("accountNumber") val accountNumber: String?,
    @SerialName("currency") val currency: String?,
    @SerialName("amount") val amount: Double?,
    @SerialName("description") val description: String?,
)

fun AccountResponse.toDomain(): Account {
    return Account(
        accountNumber = accountNumber.orEmpty(),
        currency = currency.orEmpty(),
        amount = amount ?: 0.00,
        description = description.orEmpty(),
    )
}

fun List<AccountResponse>.toDomain() = this.map { it.toDomain() }
