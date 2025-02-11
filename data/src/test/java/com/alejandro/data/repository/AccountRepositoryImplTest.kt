package com.alejandro.data.repository

import com.alejandro.data.local.room.dao.AccountDao
import com.alejandro.data.model.response.AccountResponse
import com.alejandro.data.remote.api.AccountApi
import com.alejandro.domain.util.GenericException
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class AccountRepositoryImplTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    @RelaxedMockK
    private lateinit var accountApi: AccountApi

    @RelaxedMockK
    private lateinit var accountDao: AccountDao

    private val repository by lazy { AccountRepositoryImpl(testDispatcher, accountApi, accountDao) }

    @Before
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun whenGetAccountIsInvokedItShouldReturnAListOfAccountsFromApiImpl() = runTest {
        val response = listOf(account)
        coEvery { accountApi.getAccount() } returns response

        val result = repository.getAccount()

        assertNotNull(result)
        assertEquals(response.first().accountNumber, result.first().accountNumber)
        coVerify(exactly = 1) { accountApi.getAccount() }
        coVerify(exactly = 0) { accountApi.getMovements(any()) }
    }

    @Test
    fun whenGetAccountIsInvokedItShouldReturnAErrorFromApiImpl() = runTest {
        coEvery { accountApi.getAccount() } throws GenericException(ERROR)

        runCatching {
            repository.getAccount()
        }.onSuccess {
            assertTrue(it.isNotEmpty())
        }.onFailure {
            assertTrue(it is GenericException)
            assertEquals(it.message, ERROR)
        }

        coVerify(exactly = 1) { accountApi.getAccount() }
        coVerify(exactly = 0) { accountApi.getMovements(any()) }
    }

    /**
     * data for test
     */

    private val account = AccountResponse(
        accountNumber = "987987897",
        currency = "$/",
        amount = 200.52,
        description = "Cuenta corriente",
    )

    companion object {
        const val ERROR = "ERROR MOCK"
    }
}
