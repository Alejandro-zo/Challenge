package com.alejandro.challenge.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alejandro.domain.repository.AppParameterRepository
import com.alejandro.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val appParameterRepository: AppParameterRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> get() = _uiState.asStateFlow()

    private val _formState: MutableStateFlow<LoginFormState> = MutableStateFlow(LoginFormState())
    val formState: StateFlow<LoginFormState> get() = _formState.asStateFlow()

    fun handleUiEvent(uiEvent: UiEvent) {
        when (uiEvent) {
            is UiEvent.InsertData -> insertDate()
            is UiEvent.UserChanged -> userChanged(uiEvent.value)
            is UiEvent.PasswordChanged -> passwordChanged(uiEvent.value)
            is UiEvent.ButtonClickedEnter -> buttonClickedEnter()
            is UiEvent.HideError -> _uiState.update { it.copy(error = null) }
        }
    }

    private fun insertDate() = viewModelScope.launch {
        authRepository.saveUser()
        deleteSession()
    }

    private fun deleteSession() = viewModelScope.launch {
        appParameterRepository.deleteAllParameter()
    }

    private fun userChanged(value: String) {
        _formState.update { it.copy(user = value) }
        _uiState.update { it.copy(enableButton = validateButton()) }
    }

    private fun passwordChanged(value: String) {
        _formState.update { it.copy(password = value) }
        _uiState.update { it.copy(enableButton = validateButton()) }
    }

    private fun validateButton(): Boolean {
        return _formState.value.user.isNotBlank() && _formState.value.password.isNotBlank()
    }

    private fun buttonClickedEnter() = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true) }
        try {
            authRepository.login(_formState.value.user, _formState.value.password)
            _uiState.update { it.copy(navigateToHome = true) }
        } catch (error: Throwable) {
            _uiState.update { it.copy(error = error, isLoading = false) }
        }
    }
}
