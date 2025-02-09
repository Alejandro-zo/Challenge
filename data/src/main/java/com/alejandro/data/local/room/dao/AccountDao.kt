package com.alejandro.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alejandro.data.local.room.entity.AccountDb

@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount(accountDb: AccountDb)

    @Query("select  * from account where accountNumber = :accountNumber limit 1")
    suspend fun getAccountByAccountNumber(accountNumber: String): AccountDb?

    @Query("delete from account")
    suspend fun deleteAllAccount()
}

