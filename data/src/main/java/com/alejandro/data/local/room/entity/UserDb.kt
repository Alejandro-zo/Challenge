package com.alejandro.data.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class UserDb (
    @PrimaryKey @ColumnInfo("userName") val userName: String,
    @ColumnInfo("password") val password: String,
)
