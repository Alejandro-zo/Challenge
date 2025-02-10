package com.alejandro.challenge.main

import androidx.lifecycle.ViewModel
import com.alejandro.domain.repository.AppParameterRepository
import com.alejandro.domain.util.Constants.SESSION_TIME
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val appParameterRepository: AppParameterRepository,
) : ViewModel() {

    private val _timeRemaining = MutableStateFlow(SESSION_TIME)

    private val _isSessionExpired = MutableStateFlow(false)
    val isSessionExpired: StateFlow<Boolean> get() = _isSessionExpired.asStateFlow()

    private var lastActivityTime: Long = System.currentTimeMillis() + SESSION_TIME

    suspend fun updateLastActivity() {
        _timeRemaining.value = SESSION_TIME
        lastActivityTime = appParameterRepository.getSessionTime() + SESSION_TIME
        _isSessionExpired.value = false
        startSessionTimer()
    }

    private suspend fun startSessionTimer() = withContext(ioDispatcher) {
        while (_timeRemaining.value > 0) {
            delay(1000)
            _timeRemaining.value = lastActivityTime - System.currentTimeMillis()

            if (_timeRemaining.value <= 0) {
                _isSessionExpired.value = true
                deleteSession()
                break
            }
        }
    }

    private suspend fun deleteSession() = withContext(ioDispatcher) {
        appParameterRepository.deleteAllParameter()
    }
}
