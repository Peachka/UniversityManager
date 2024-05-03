package com.example.university.ui.create_account

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CreateAccount(
    viewModel: CreateAccountViewModel = hiltViewModel(),
    goToLoginScreen: () -> Unit
) {

    val state = viewModel.state.value

    CreateAccountScreen(
        state = state,
        goToLoginScreen = goToLoginScreen,
        addUser = { email, password, name, secondName, group, goToLoginScreen ->
            viewModel.createUser(
                email,
                password,
                name,
                secondName,
                group,
                goToLoginScreen
            )
        }

    )

}