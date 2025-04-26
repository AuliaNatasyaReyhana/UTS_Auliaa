package com.example.todolist_aulia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todolist_aulia.ui.screens.LoginScreen
import com.example.todolist_aulia.ui.screens.MainScreen
import com.example.todolist_aulia.viewmodel.TaskViewModel

class MainActivity : ComponentActivity() {
    private val viewModel = TaskViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigator(viewModel)
        }
    }
}

@Composable
fun AppNavigator(viewModel: TaskViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController)
        }
        composable("main") {
            MainScreen(viewModel, navController)
        }
    }
}
