package com.alejandro.domain.usecase.account

import com.alejandro.domain.entity.Account
import com.alejandro.domain.repository.AccountRepository
import javax.inject.Inject

class SaveAccountUseCase @Inject constructor(private val accountRepository: AccountRepository) {
    suspend operator fun invoke(account: Account) {
        return accountRepository.saveAccount(account)
    }
}
