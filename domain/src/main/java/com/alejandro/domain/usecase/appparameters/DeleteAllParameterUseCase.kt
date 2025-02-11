package com.alejandro.domain.usecase.appparameters

import com.alejandro.domain.repository.AppParameterRepository
import javax.inject.Inject

class DeleteAllParameterUseCase @Inject constructor(private val appParameterRepository: AppParameterRepository) {
    suspend operator fun invoke() {
        appParameterRepository.deleteAllParameter()
    }
}
