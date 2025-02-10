package com.alejandro.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alejandro.data.local.room.entity.AppParameterDb

@Dao
interface AppParameterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppParameter(appParameterDb: AppParameterDb)

    @Query("select * from appParameter where `key` = :parameter")
    suspend fun getAppParameter(parameter: String): AppParameterDb?

    @Query("delete from appParameter")
    suspend fun deleteAllParameter()
}
