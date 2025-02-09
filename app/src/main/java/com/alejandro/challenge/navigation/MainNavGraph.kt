package com.alejandro.challenge.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.alejandro.challenge.feature.home.HomeScreen
import com.alejandro.challenge.feature.productdetail.ProductDetailScreen

fun NavGraphBuilder.authGraph(
    navController: NavController,
    navigationToBack: () -> Unit
) {
    navigation<MainGraph>(startDestination = HomeDestination) {

        composable<HomeDestination> {
            HomeScreen(
                navigateToLogin = {},
                navigateToAccountDetail = {
                    navController.navigate(ProductDetailDestination(it))
                },
            )
        }

        composable<ProductDetailDestination> {
            ProductDetailScreen(
                navigateToBack = navigationToBack,
                navigateToLogin = {
                    navController.navigate(
                        route = LoginDestination,
                        navOptions = navOptions {
                            popUpTo(navController.graph.id) { inclusive = true }
                        }
                    )
                },
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
