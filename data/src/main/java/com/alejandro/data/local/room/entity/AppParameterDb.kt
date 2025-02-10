package com.alejandro.data.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "appParameter")
class AppParameterDb (
    @PrimaryKey  @ColumnInfo("key") val key :String,
    @ColumnInfo("value") val value :String,
)
