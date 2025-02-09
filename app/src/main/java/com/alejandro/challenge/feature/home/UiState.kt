package com.alejandro.challenge.feature.home

import com.alejandro.challenge.R
import com.alejandro.domain.entity.Account
import com.alejandro.domain.util.Constants.EMPTY_STRING

data class UiState(
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val listAccount: List<Account> = emptyList(),
    val navigateToHome: Boolean = false,
    val navigateToAccountDetail: Boolean = false,
    val error: Throwable? = null,
    val errorData: Boolean = false,
    val accountNumber: String =  EMPTY_STRING,
    val messageErrorData: Int = R.string.failed_to_get_accounts,
)
