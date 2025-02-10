package com.alejandro.challenge.main

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.alejandro.challenge.components.session.InactivityHandle
import com.alejandro.challenge.navigation.AppNavigation
import com.alejandro.challenge.navigation.LoginDestination
import com.alejandro.challenge.theme.ChallengeTheme
import com.alejandro.challenge.theme.surfaceLight
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val color = surfaceLight

        installSplashScreen()
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(color.toArgb(), color.toArgb()),
            navigationBarStyle = SystemBarStyle.light(color.toArgb(), color.toArgb()),
        )
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        setContent {
            val navController = rememberNavController()
            ChallengeTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.surface),
                    content = {
                        Box(modifier = Modifier.fillMaxSize()) { AppNavigation(navController) }

                        InactivityHandle { navigateToLogin(navController) }
                    }
                )

            }
        }
    }

    private fun navigateToLogin(navController: NavController) {
        navController.navigate(
            route = LoginDestination,
            navOptions = navOptions {
                popUpTo(navController.graph.id) { inclusive = true }
            }
        )
    }
}
