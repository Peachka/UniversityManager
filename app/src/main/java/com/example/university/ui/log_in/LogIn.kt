package com.example.university.ui.log_in

import androidx.compose.runtime.Composable

@Composable
fun LogIn(goToCreateAcc: () -> Unit) {
    LogInScreen(goToCreateAccount = goToCreateAcc)

}