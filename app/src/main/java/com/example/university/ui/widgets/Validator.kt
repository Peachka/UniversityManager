package com.example.university.ui.widgets

import com.example.university.R
import com.example.university.data.local.login.User
import com.example.university.di.StringResourceProvider
import com.example.university.ui.log_in.UserLogIn
import javax.inject.Inject

class Validator @Inject constructor(
    private val stringResourceProvider: StringResourceProvider
){

    fun validateLogInUser(email: String, password: String, users: List<UserLogIn>):String{

        val userInList = users.any { user -> user.email.equals(email, ignoreCase = true)}

        if (userInList) {
            var userInDB = users.find { it.email == email }
            if (userInDB != null) {
                if (password == userInDB.password){
                    return stringResourceProvider.getString(R.string.correct_input)
                } else {
                    return stringResourceProvider.getString(R.string.wrong_pass)
                }
            }

        }
        return stringResourceProvider.getString(R.string.incorrect_email)
    }

    /*TODO: VALIDATE SEPARETELY EMAIL AND GROUP*/
    fun validateCreateUser(email: String, group: String):Boolean{
        if ("@" in email && group.length == 5){
            return true
        }
        return false
    }
}