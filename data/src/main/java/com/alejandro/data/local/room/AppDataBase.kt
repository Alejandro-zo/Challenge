package com.alejandro.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alejandro.data.local.room.dao.AccountDao
import com.alejandro.data.local.room.entity.AccountDb

@Database(
    version = 1,
    entities = [
        AccountDb::class,
    ]
)

abstract class AppDataBase : RoomDatabase() {

    abstract fun accountDao(): AccountDao
}
