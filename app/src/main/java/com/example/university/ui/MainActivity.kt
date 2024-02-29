package com.example.university.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.university.ui.log_in.LogInScreen
import com.example.university.ui.navigation.Screen
import com.example.university.ui.theme.UniversityTheme

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

    NavHost(navController = navController, startDestination = Screen.LogIn.route){
        composable(Screen.LogIn.route){ LogInScreen()}
        composable(Screen.Home.route){}
    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    UniversityTheme {
//        Greeting("Android")
//    }
//}