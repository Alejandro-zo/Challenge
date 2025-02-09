package com.alejandro.challenge.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.alejandro.challenge.feature.home.HomeScreen

fun NavGraphBuilder.authGraph(navigationToBack: () -> Unit) {
    navigation<MainGraph>(startDestination = HomeDestination) {

        composable<HomeDestination> {
            HomeScreen(
                navigateToLogin = {},
                navigateToAccountDetail = { },
            )
        }
    }
}

fun NavController.navigateToAuthGraph(navOptions: NavOptions? = null) {
    navigate(
        route = MainGraph,
        navOptions = navOptions ?: navOptions { launchSingleTop = true }
    )
}
