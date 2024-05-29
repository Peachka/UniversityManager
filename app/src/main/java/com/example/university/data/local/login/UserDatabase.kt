package com.example.university.data.local.login

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = [User::class], exportSchema = false, version = 1)
abstract class UserDatabase: RoomDatabase(){
    abstract fun userDAO(): UserDAO
}