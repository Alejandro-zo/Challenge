package com.alejandro.domain.usecase.account

import com.alejandro.domain.entity.Account
import com.alejandro.domain.repository.AccountRepository
import com.alejandro.domain.util.GenericException
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetAccountUseCaseTest {

    @RelaxedMockK
    private lateinit var accountRepository: AccountRepository

    private val getCredentialNameUseCase by lazy { GetAccountUseCase(accountRepository) }

    @Before
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun whenUseCaseIsInvokedItShouldReturnAListOfAccountsFromRepository() = runTest {
        val response = listOf(account)
        coEvery { accountRepository.getAccount() } returns response

        val result = getCredentialNameUseCase()

        assertNotNull(result)
        assertEquals(response.first().accountNumber, result.first().accountNumber)
        coVerify(exactly = 1) { accountRepository.getAccount() }
        coVerify(exactly = 0) { accountRepository.getMovements(any()) }
        coVerify(exactly = 0) { accountRepository.updateAccount() }
        coVerify(exactly = 0) { accountRepository.saveAccount(any()) }
        coVerify(exactly = 0) { accountRepository.getMovements(any()) }
    }

    @Test
    fun whenUseCaseIsInvokedItShouldReturnAErrorFromRepository() = runTest {
        coEvery { accountRepository.getAccount() } throws GenericException(ERROR)

        runCatching {
            getCredentialNameUseCase()
        }.onFailure {
            assertTrue(it is GenericException)
            assertEquals(it.message, ERROR)
        }

        coVerify(exactly = 1) { accountRepository.getAccount() }
        coVerify(exactly = 0) { accountRepository.getMovements(any()) }
        coVerify(exactly = 0) { accountRepository.updateAccount() }
        coVerify(exactly = 0) { accountRepository.saveAccount(any()) }
        coVerify(exactly = 0) { accountRepository.getMovements(any()) }
    }

    /**
     * data for test
     */

    private val account = Account(
        accountNumber = "987987897",
        currency = "$/",
        amount = 200.52,
        description = "Cuenta corriente",
    )

    companion object {
        const val ERROR = "ERROR MOCK"
    }
}
