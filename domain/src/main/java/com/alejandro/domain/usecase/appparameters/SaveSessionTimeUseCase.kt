package com.alejandro.domain.usecase.appparameters

import com.alejandro.domain.repository.AppParameterRepository
import javax.inject.Inject

class SaveSessionTimeUseCase @Inject constructor(private val appParameterRepository: AppParameterRepository) {
    suspend operator fun invoke(value: Long) {
        appParameterRepository.saveSessionTime(value)
    }
}
