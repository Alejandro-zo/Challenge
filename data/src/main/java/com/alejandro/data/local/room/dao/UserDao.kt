package com.alejandro.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alejandro.data.local.room.entity.UserDb

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userDb: UserDb)

    @Query("select  * from user where userName = :userName and password = :password limit 1")
    suspend fun getUser(userName: String, password: String): UserDb?

    @Query("delete from user")
    suspend fun deleteAllUser()
}
