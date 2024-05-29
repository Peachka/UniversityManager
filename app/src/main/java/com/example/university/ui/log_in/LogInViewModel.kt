package com.example.university.ui.log_in

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.university.data.local.login.User
import com.example.university.ui.create_account.UserState
import com.example.university.ui.log_in.repository.UserRepositoryImpl
import com.example.university.ui.widgets.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UserLogIn(
    val id: Int,
    val email: String,
    val password: String
)

data class UserInput(
    val emailError: Boolean = false,
    val passwordError: Boolean = false

)
@HiltViewModel
class LogInViewModel @Inject constructor(
    private val userDao: UserRepositoryImpl,
    private val validator: Validator
) : ViewModel() {

    var _inputState: MutableState<UserInput> = mutableStateOf(UserInput())
    val inputState: State<UserInput> = _inputState

    var _usersFromDB: MutableState<List<UserLogIn>> = mutableStateOf<List<UserLogIn>>(emptyList())
    val usersFromDB: List<UserLogIn> = _usersFromDB.value
    init {
        viewModelScope.launch {
            userDao.getAll()
                .map { userList ->
                    userList.map { user ->
                        UserLogIn(user.uid, user.email, user.password)
                    }
                }
                .collect { userLogInList ->
                    _usersFromDB.value = userLogInList
                }
        }
    }

    fun validateInputName(email: String, password: String): Boolean {
        val errorString = validator.validateLogInUser(email, password, _usersFromDB.value)
        Log.d("ViewModel", "validateInputName is triggered - error $errorString")
        val inputStateValue = when {
            "email" in errorString -> UserInput(emailError = true)
            "password" in errorString -> UserInput(emailError = false, passwordError = true)
            else -> UserInput(emailError = false, passwordError = false)
        }
        _inputState.value = inputStateValue
        return inputStateValue.emailError.not() && inputStateValue.passwordError.not()
    }

    fun addIdToFile(email: String, password: String, onComplete: () -> Unit){
        val user = _usersFromDB.value.filter { it.email == email && it.password == password }
        viewModelScope.launch {
            userDao.addUserToPreferences(user[0].id)
            onComplete()
        }
    }
}