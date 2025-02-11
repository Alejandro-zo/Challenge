package com.alejandro.domain.usecase.account

import com.alejandro.domain.entity.Account
import com.alejandro.domain.repository.AccountRepository
import javax.inject.Inject

class GetAccountByAccountNumberUseCase @Inject constructor(private val accountRepository: AccountRepository) {
    suspend operator fun invoke(accountNumber: String): Account {
        return accountRepository.getAccountByAccountNumber(accountNumber = accountNumber)
    }
}
