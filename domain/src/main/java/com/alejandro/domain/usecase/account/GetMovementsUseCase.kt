package com.alejandro.domain.usecase.account

import com.alejandro.domain.entity.Movement
import com.alejandro.domain.repository.AccountRepository
import javax.inject.Inject

class GetMovementsUseCase @Inject constructor(private val accountRepository: AccountRepository) {
    suspend operator fun invoke(accountNumber: String): List<Movement> {
        return accountRepository.getMovements(accountNumber = accountNumber)
    }
}
