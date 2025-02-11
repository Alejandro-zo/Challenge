package com.alejandro.domain.usecase.appparameters

import com.alejandro.domain.repository.AppParameterRepository
import javax.inject.Inject

class GetSessionTimeUseCase @Inject constructor(private val appParameterRepository: AppParameterRepository) {
    suspend operator fun invoke(): Long {
        return appParameterRepository.getSessionTime()
    }
}
