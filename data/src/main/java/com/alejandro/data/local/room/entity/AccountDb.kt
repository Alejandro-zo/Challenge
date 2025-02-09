package com.alejandro.data.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alejandro.domain.entity.Account

@Entity(tableName = "account")
class AccountDb(
    @PrimaryKey @ColumnInfo("accountNumber") val accountNumber: String,
    @ColumnInfo("currency") val currency: String,
    @ColumnInfo("amount") val amount: Double,
    @ColumnInfo("description") val description: String,
)

fun Account.totoDataBase(): AccountDb {
    return AccountDb(
        accountNumber = accountNumber,
        currency = currency,
        amount = amount,
        description = description,
    )
}

fun AccountDb.toDomain(): Account {
    return Account(
        accountNumber = accountNumber,
        currency = currency,
        amount = amount,
        description = description,
    )
}
