package com.alejandro.challenge.feature.productdetail

import com.alejandro.domain.entity.Account
import com.alejandro.domain.entity.Movement

data class UiState(
    val isLoading: Boolean = true,
    val account: Account? = null,
    val listMovements: List<Movement> = emptyList(),
    val navigateToLogin: Boolean = false,
    val error: Throwable? = null,
)
