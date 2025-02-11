package com.alejandro.domain.usecase.auth

import com.alejandro.domain.repository.AuthRepository
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke() {
        authRepository.saveUser()
    }
}
