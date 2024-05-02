package com.example.university.ui.create_account

import androidx.compose.runtime.Composable
import javax.inject.Inject
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CreateAccount (
    viewModel: CreateAccountViewModel = hiltViewModel(),
    goToLoginScreen: () -> Unit
){

    CreateAccountScreen(
        goToLoginScreen = goToLoginScreen,
        addUser = { viewModel.addUser(it) }
        )

}