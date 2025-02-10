package com.alejandro.challenge.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alejandro.challenge.feature.login.LoginScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = LoginDestination) {
        composable<LoginDestination> {
            LoginScreen(
                navigateToHome = {
                    navController.popBackStack()
                    navController.navigateToAuthGraph()
                }
            )
        }

        authGraph(navController,navController::navigateToBack)

    }
}

private fun NavController.navigateToBack() {
    popBackStack()
}
