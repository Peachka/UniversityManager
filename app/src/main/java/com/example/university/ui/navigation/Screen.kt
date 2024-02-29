package com.example.university.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object LogIn : Screen("log-in")
    object Settings : Screen("settings")
}
