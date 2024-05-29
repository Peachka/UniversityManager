package com.example.university.data.local.login

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {
    @Query("SELECT * FROM user")
    fun getAll(): Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: User)

    @Update(entity = User::class)
    fun editAccount(user: User)

    @Query("SELECT * FROM user WHERE uid = :uid LIMIT 1")
    suspend fun getUserById(uid: Int): User?
}