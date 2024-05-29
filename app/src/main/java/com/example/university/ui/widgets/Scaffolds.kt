package com.example.university.ui.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.university.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenWithBars(navController: NavController, title: String, content: @Composable () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(title) })
        },
        bottomBar = {
            BottomNavigationBar(navController)
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                content()
            }
        }
    )
}

@Composable
fun ScreenWithoutBars(content: @Composable () -> Unit) {
    Scaffold(
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                content()
            }
        }
    )
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation {
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text(Screen.Schedule.route) },
            selected = false, // Add your logic to handle selected state

            onClick = { navController.popBackStack(Screen.Schedule.route, inclusive = false) }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Person, contentDescription = null) },
            label = { Text(Screen.News.route) },
            selected = false, // Add your logic to handle selected state
            onClick = { navController.navigate(Screen.News.route) }
        )
    }
}