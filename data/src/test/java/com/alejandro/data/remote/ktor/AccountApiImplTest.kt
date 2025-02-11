package com.alejandro.data.remote.ktor

import com.alejandro.data.model.response.AccountResponse
import com.alejandro.data.remote.GET_ACCOUNT
import com.alejandro.data.remote.createMockKtor
import com.alejandro.data.remote.responseSuccessConvertToJson
import com.alejandro.domain.util.GenericException
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import io.mockk.MockKAnnotations
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class AccountApiImplTest {
    private lateinit var mockEngine: MockEngine
    private lateinit var mockKtor: HttpClient
    private lateinit var accountApiImpl: AccountApiImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun whenGetAccountIsInvokedItShouldReturnAListOfAccountsFromTheService() = runTest {
        val cardResponse = listOf(accountResponse)
        val response = responseSuccessConvertToJson<List<AccountResponse>>(cardResponse)
        mockEngine = MockEngine {
            respond(
                content = ByteReadChannel(response),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        mockKtor = createMockKtor(mockEngine)
        accountApiImpl = AccountApiImpl(mockKtor)

        val result = accountApiImpl.getAccount()

        assertEquals(cardResponse.first().accountNumber, accountResponse.accountNumber)
        assertTrue(result.isNotEmpty())

        // Verify request
        with(mockEngine.requestHistory.first()) {
            assertEquals("GET", method.value)
            assertEquals("/$GET_ACCOUNT", url.encodedPath)
        }
    }

    @Test
    fun whenGetAccountIsInvokedItShouldReturnAErrorHttpFromTheService() = runTest {
        val cardResponse = listOf(accountResponse)
        val response = responseSuccessConvertToJson<List<AccountResponse>>(cardResponse)
        mockEngine = MockEngine {
            respond(
                content = ByteReadChannel(response),
                status = HttpStatusCode.BadRequest,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        mockKtor = createMockKtor(mockEngine)
        accountApiImpl = AccountApiImpl(mockKtor)

        runCatching {
            accountApiImpl.getAccount()
        }.onSuccess {
            assertTrue(it.isNotEmpty())
        }.onFailure {
            assertTrue(it is GenericException)
        }

        // Verify request
        with(mockEngine.requestHistory.first()) {
            assertEquals("GET", method.value)
            assertEquals("/$GET_ACCOUNT", url.encodedPath)
        }
    }

    /**
     * data for test
     */

    private val accountResponse = AccountResponse(
        accountNumber = "987987897",
        currency = "$/",
        amount = 200.52,
        description = "Cuenta corriente",
    )
}
