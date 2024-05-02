package com.example.university.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object LogIn : Screen("log-in")
    object CreateAccount : Screen("create_account")
    object Settings : Screen("settings")
}
