package com.example.university.ui.create_account

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.university.data.local.login.User
import com.example.university.ui.log_in.repository.UserDaoRepository
import com.example.university.ui.widgets.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class UserState{
    object Initial: UserState()
    data class InputError(
        var emptyEmail: Boolean = false,
        var emptyPassword: Boolean = false,
        var emptyName: Boolean = false,
        var emptySecondName: Boolean = false,
        var emptyGroup: Boolean = false
    ) : UserState()
    object Success: UserState()
}
@HiltViewModel
class CreateAccountViewModel @Inject constructor(
    private val userRepo: UserDaoRepository,
    private val validator: Validator
): ViewModel() {

    private val _state = mutableStateOf<UserState>(UserState.Initial)
    val state: State<UserState> = _state
    fun createUser(
        email: String,
        password: String,
        name: String,
        secondName: String,
        group: String,
        goToLoginScreen: () -> Unit
    ){
        if (email.isBlank() || name.isBlank() || password.isBlank()|| secondName.isBlank() || group.isBlank()) {
            _state.value = UserState.InputError(
                emptyEmail = email.isBlank(),
                emptyPassword = password.isBlank(),
                emptyName = name.isBlank(),
                emptySecondName = secondName.isBlank(),
                emptyGroup = group.isBlank()
            )
        }
        if(!validator.validateCreateUser(email, group)){
            _state.value = UserState.InputError(
                emptyEmail = true,
                emptyPassword = password.isBlank(),
                emptyName = name.isBlank(),
                emptySecondName = secondName.isBlank(),
                emptyGroup = true
            )
        }
        else {
            addUser(
                User(
                email = email,
                password = password,
                name = name,
                secondName = secondName,
                group = group
            )
            )
            goToLoginScreen()
            _state.value = UserState.Success
        }
    }
    fun addUser(user: User){
        viewModelScope.launch {
            userRepo.addUser(user)
            userRepo.saveUserId(user.uid)
        }
    }
}