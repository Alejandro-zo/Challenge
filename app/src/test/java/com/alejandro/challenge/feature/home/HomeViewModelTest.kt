package com.alejandro.challenge.feature.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alejandro.domain.entity.Account
import com.alejandro.domain.usecase.account.GetAccountUseCase
import com.alejandro.domain.usecase.account.SaveAccountUseCase
import com.alejandro.domain.usecase.account.UpdateAccountUseCase
import com.alejandro.domain.util.GenericException
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {
    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var getAccountUseCase: GetAccountUseCase

    @RelaxedMockK
    private lateinit var updateAccountUseCase: UpdateAccountUseCase

    @RelaxedMockK
    private lateinit var saveAccountUseCase: SaveAccountUseCase


    private lateinit var viewModelTest: HomeViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModelTest = HomeViewModel(getAccountUseCase, updateAccountUseCase, saveAccountUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun whenEventServiceDataInvokedShouldInvokedGetAccountUseCase() = runTest {
        val response = listOf(account)
        coEvery { getAccountUseCase() } returns response

        val uiState = viewModelTest.uiState.value

        assertTrue(uiState.listAccount.isNotEmpty())
        assertFalse(uiState.isLoading)
        assertFalse(uiState.errorData)

        coVerify(exactly = 1) { getAccountUseCase() }
        coVerify(exactly = 0) { updateAccountUseCase() }
        coVerify(exactly = 0) { saveAccountUseCase(any()) }
    }

    @Test
    fun whenEventServiceDataIsErrorGetAccountUseCase() = runTest {
        coEvery { getAccountUseCase() } throws GenericException(ERROR)

        runCatching {
            viewModelTest.handleUiEvent(UiEvent.ServiceData)
        }.onFailure {

            val uiState = viewModelTest.uiState.value

            assertNotNull(uiState.error)
            assertEquals(uiState.error?.message, ERROR)
            assertFalse(uiState.isLoading)
            assertTrue(uiState.errorData)
        }

        coVerify(exactly = 1) { getAccountUseCase() }
        coVerify(exactly = 0) { updateAccountUseCase() }
        coVerify(exactly = 0) { saveAccountUseCase(any()) }
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
