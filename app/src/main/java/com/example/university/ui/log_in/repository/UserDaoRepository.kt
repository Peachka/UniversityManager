package com.example.university.ui.log_in.repository

import com.example.university.data.local.login.User
import kotlinx.coroutines.flow.Flow

interface UserDaoRepository{
    fun getAll(): Flow<List<User>>
    suspend fun addUser(user: User)
    suspend fun editAccount(user: User)
    suspend fun getUserById(uid: Int): User?
    suspend fun saveUserId(uid: Int)

}