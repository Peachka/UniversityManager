package com.example.university.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.university.ui.create_account.CreateAccount
import com.example.university.ui.create_account.CreateAccountScreen
import com.example.university.ui.log_in.LogIn
import com.example.university.ui.log_in.LogInScreen
import com.example.university.ui.navigation.Screen
import com.example.university.ui.news.News
import com.example.university.ui.schedule.Schedule
import com.example.university.ui.theme.UniversityTheme
import com.example.university.ui.widgets.ScreenWithBars
import com.example.university.ui.widgets.ScreenWithoutBars
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UniversityTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                NavGraph(navController = navController)


            }
        }
    }
}

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(modifier = Modifier.fillMaxSize().safeDrawingPadding(),
        navController = navController,
        startDestination = Screen.LogIn.route) {

        composable(Screen.LogIn.route) {
            ScreenWithoutBars {
                LogIn(goToCreateAcc = { navController.navigate(Screen.CreateAccount.route) },
                    successLogIn = { navController.navigate(Screen.Schedule.route) })
            }
        }
        composable(Screen.CreateAccount.route) {
            ScreenWithoutBars {
                CreateAccount(goToLoginScreen = {
                    navController.navigate(
                        Screen.LogIn.route
                    )
                })
            }
        }
        composable(Screen.Schedule.route) {
            ScreenWithBars(navController, "Schedule") {
                Schedule()
            }
        }

        composable(Screen.News.route) {
            ScreenWithBars(navController, "News") {
                News()
            }
        }
    }
}
