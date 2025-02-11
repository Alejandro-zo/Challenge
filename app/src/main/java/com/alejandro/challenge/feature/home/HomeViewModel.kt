package com.alejandro.challenge.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alejandro.challenge.R
import com.alejandro.domain.entity.Account
import com.alejandro.domain.usecase.account.GetAccountUseCase
import com.alejandro.domain.usecase.account.SaveAccountUseCase
import com.alejandro.domain.usecase.account.UpdateAccountUseCase
import com.alejandro.domain.util.AccountException
import com.alejandro.domain.util.UpdateAccountException
import com.alejandro.domain.util.isUnknownHostException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAccountUseCase: GetAccountUseCase,
    private val updateAccountUseCase: UpdateAccountUseCase,
    private val saveAccountUseCase: SaveAccountUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> get() = _uiState.asStateFlow()

    private var isLoadedData = false

    fun handleUiEvent(uiEvent: UiEvent) {
        when (uiEvent) {
            is UiEvent.ServiceData -> getAccount()
            is UiEvent.Retry -> retryGetAccount()
            is UiEvent.OnRefresh -> updateAccount()
            is UiEvent.ClickAccount -> clickAccount(uiEvent.account)
            is UiEvent.ResetNavigation -> resetNavigation()
            is UiEvent.HideError -> hideError()
        }
    }

    private fun getAccount() = viewModelScope.launch {
        if (isLoadedData) return@launch
        try {
            val result = getAccountUseCase()
            _uiState.update { it.copy(isLoading = false, listAccount = result, errorData = false) }
            isLoadedData = true
        } catch (error: Throwable) {
            val customError = if (!error.isUnknownHostException()) AccountException() else error
            handleError(customError, R.string.failed_to_get_accounts)
        }
    }

    private fun retryGetAccount() {
        _uiState.update { it.copy(error = null, isLoading = true) }
        getAccount()
    }

    private fun updateAccount() = viewModelScope.launch {
        _uiState.update { it.copy(isRefreshing = true) }
        try {
            val result = updateAccountUseCase()
            _uiState.update {
                it.copy(
                    isRefreshing = false,
                    listAccount = result,
                    errorData = false
                )
            }
        } catch (error: Throwable) {
            val customError =
                if (!error.isUnknownHostException()) UpdateAccountException() else error
            handleError(customError, R.string.failed_to_update_accounts)
        }
    }

    private fun handleError(error: Throwable, messageRes: Int) {
        _uiState.update {
            it.copy(
                isLoading = false,
                isRefreshing = false,
                error = error,
                errorData = true,
                messageErrorData = messageRes,
                listAccount = emptyList()
            )
        }
    }

    private fun hideError() {
        _uiState.update { it.copy(error = null) }
    }

    private fun clickAccount(account: Account) = viewModelScope.launch {
        saveAccountUseCase(account)
        _uiState.update {
            it.copy(
                accountNumber = account.accountNumber,
                navigateToAccountDetail = true
            )
        }
    }

    private fun resetNavigation() {
        _uiState.update { it.copy(navigateToAccountDetail = false) }
    }
}
