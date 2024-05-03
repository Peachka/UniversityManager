package com.example.university.ui.log_in

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.university.data.login.User
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

    val users = viewModelScope.launch {
        userDao.getAll()
            .map { userList ->
                userList.map { user ->
                    UserLogIn(user.email, user.password)
                }
            }
            .collect { userLogInList ->
                _usersFromDB.value = userLogInList
            }

    }
    fun validateInputName(email: String, password: String){
        val errorString = validator.validateLogInUser(email, password, _usersFromDB.value)
        Log.d("ViewModel", "validateInputName is triggered - error $errorString")
        if("email" in errorString){
            _inputState.value = UserInput(emailError = true)
        }
        else if("password" in errorString){
            _inputState.value = UserInput(emailError = false, passwordError = true)

        } else {
            _inputState.value = UserInput(emailError = false, passwordError = false)
        }

    }

}