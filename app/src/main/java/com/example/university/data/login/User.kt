package com.example.university.data.login

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0, // Set the default value to 0
    val email: String,
    val password: String,
    val name: String,
    val secondName: String,
    val group: String,
    //val  photo: Int
)