package com.example.university.ui.log_in

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import javax.inject.Inject

@Composable
fun LogIn (
    viewModel: LogInViewModel = hiltViewModel(),
    goToCreateAcc: () -> Unit) {

    val inputState = viewModel.inputState.value

    LogInScreen(
        inputState = inputState,
        goToCreateAccount = goToCreateAcc,
        validateInput = {email, password -> viewModel.validateInputName( email = email, password = password)}
        )

}