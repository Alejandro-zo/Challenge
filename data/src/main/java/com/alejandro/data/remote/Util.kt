package com.alejandro.data.remote

import com.alejandro.data.model.response.ResponseWrapper
import com.alejandro.domain.util.GenericException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode

suspend inline fun <reified T> validate(response: HttpResponse): T? {
    return try {
        val responseWrapper = response.body<ResponseWrapper<T>>()

        when {
            response.status == HttpStatusCode.OK && responseWrapper.success == true -> {
                responseWrapper.data
            }
            else -> throw GenericException()

        }
    } catch (e: Exception) {
        throw GenericException()
    }
}
