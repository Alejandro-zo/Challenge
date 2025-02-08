package com.alejandro.challenge.feature.login

data class UiState(
    val isLoading: Boolean = false,
    val enableButton: Boolean = false,
    val navigateToHome: Boolean = false,
    val error: Throwable? = null,
)
