package com.alejandro.challenge.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alejandro.challenge.feature.login.LoginScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = LoginDestination) {
        composable<LoginDestination> {
            LoginScreen(navigateToHome = {})
        }

    }
}

private fun NavController.navigateToBack() {
    popBackStack()
}
