package com.alejandro.domain.usecase.auth

import com.alejandro.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(userName: String, password: String) {
        authRepository.login(userName = userName, password = password)
    }
}
