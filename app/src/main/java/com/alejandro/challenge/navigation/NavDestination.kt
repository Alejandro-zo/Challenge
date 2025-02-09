package com.alejandro.challenge.navigation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Auth
 */

@Serializable
@SerialName("LoginDestination")
data object LoginDestination

/**
 * Home
 */

@Serializable
@SerialName("MainGraph")
data object MainGraph

@Serializable
@SerialName("HomeDestination")
data object HomeDestination

