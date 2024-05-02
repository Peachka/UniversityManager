package com.example.university.ui.log_in.repository

import com.example.university.data.login.User
import com.example.university.data.login.UserDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    val userDAO: UserDAO
): UserDaoRepository {
     override fun getAll(): Flow<List<User>> {
        return userDAO.getAll()
    }

    override suspend fun addUser(user: User) {
        withContext(Dispatchers.IO){
            userDAO.addUser(user)
        }
    }

    override suspend fun editAccount(user: User) {
        withContext(Dispatchers.IO){
            userDAO.editAccount(user)
        }
    }
}