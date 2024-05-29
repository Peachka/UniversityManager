package com.example.university.ui.log_in

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import javax.inject.Inject

@Composable
fun LogIn (
    viewModel: LogInViewModel = hiltViewModel(),
    goToCreateAcc: () -> Unit,
    successLogIn: () -> Unit) {

    val inputState by viewModel.inputState

    LogInScreen(
        inputState = inputState,
        goToCreateAccount = goToCreateAcc,
        validateInput = {email, password -> viewModel.validateInputName( email = email, password = password)},
        successLogIn = successLogIn,
        saveIdToFile = { email, password, onComplete ->
            viewModel.addIdToFile(email = email, password = password, onComplete = onComplete)
        }
    )
}