package com.alejandro.data.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account")
class AccountDb (
    @PrimaryKey @ColumnInfo("account") val account: String,
    @ColumnInfo("currency") val currency: String,
    @ColumnInfo("amount") val amount: Double,
    @ColumnInfo("description") val description: String,
)
