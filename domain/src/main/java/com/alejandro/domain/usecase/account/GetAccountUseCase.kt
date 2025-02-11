package com.alejandro.domain.usecase.account

import com.alejandro.domain.entity.Account
import com.alejandro.domain.repository.AccountRepository
import javax.inject.Inject

class GetAccountUseCase @Inject constructor(private val accountRepository: AccountRepository) {
    suspend operator fun invoke(): List<Account> {
        return accountRepository.getAccount()
    }
}
