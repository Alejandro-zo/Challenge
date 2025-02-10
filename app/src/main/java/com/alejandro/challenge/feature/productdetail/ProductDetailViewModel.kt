package com.alejandro.challenge.feature.productdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alejandro.domain.repository.AccountRepository
import com.alejandro.domain.util.Constants.EMPTY_STRING
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val accountRepository: AccountRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> get() = _uiState.asStateFlow()

    private val accountNumber: String by lazy { savedStateHandle["accountNumber"] ?: EMPTY_STRING }

    init {
        getAccountDetail()
    }

    fun handleUiEvent(uiEvent: UiEvent) {
        when (uiEvent) {
            is UiEvent.ServiceData -> getMovements()
            is UiEvent.HideError -> _uiState.update { it.copy(error = null) }
        }
    }

    private fun getAccountDetail() = viewModelScope.launch {
        try {
            val result = accountRepository.getAccountByAccountNumber(accountNumber)
            _uiState.update { it.copy(account = result) }
        } catch (error: Throwable) {
            handleError(error)
        }
    }

    private fun getMovements() = viewModelScope.launch {
        try {
            val result = accountRepository.getMovements(accountNumber)
            _uiState.update { it.copy(isLoading = false, listMovements = result) }
        } catch (error: Throwable) {
            handleError(error)
        }
    }

    private fun handleError(error: Throwable) {
        _uiState.update { it.copy(isLoading = false, error = error) }
    }
}
