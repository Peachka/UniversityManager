package com.example.university.ui.log_in.repository

import com.example.university.data.local.login.User
import com.example.university.data.local.login.UserDAO
import com.example.university.data.shared_preferences.UserPreferencesManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userDAO: UserDAO,
    private val userPreferencesManager: UserPreferencesManager

): UserDaoRepository {
     override fun getAll(): Flow<List<User>> {
        return userDAO.getAll()
    }

    override suspend fun addUser(user: User) {
        withContext(Dispatchers.IO){
            userDAO.addUser(user)
            userPreferencesManager.saveUserId(user.uid)
        }
    }

    override suspend fun editAccount(user: User) {
        withContext(Dispatchers.IO){
            userDAO.editAccount(user)
        }
    }
    fun getUserIdFlow() = userPreferencesManager.userIdFlow

    suspend fun addUserToPreferences(userId: Int){
        userPreferencesManager.saveUserId(userId)
    }

    override suspend fun getUserById(uid: Int): User? {
        return userDAO.getUserById(uid)
    }

    override suspend fun saveUserId(uid: Int) {
        userPreferencesManager.saveUserId(uid)
    }
}