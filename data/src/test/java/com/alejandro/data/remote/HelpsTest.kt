package com.alejandro.data.remote

import com.alejandro.data.model.response.ResponseWrapper
import com.alejandro.domain.util.Constants.EMPTY_STRING
import io.github.serpro69.kfaker.Faker
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

inline fun <reified T> convertToJson(data: T): String {
    return try {
        Json.encodeToString(data)
    } catch (_: Exception) {
        EMPTY_STRING
    }
}

inline fun <reified T> responseSuccessConvertToJson(data: T?): String {
    val faker = Faker()
    return try {
        val response = ResponseWrapper(
            success = true,
            message = faker.random.randomString(length = 10),
            data = data,
        )
        Json.encodeToString(response)
    } catch (_: Exception) {
        EMPTY_STRING
    }
}

inline fun <reified T> responseErrorConvertToJson(data: T): String {
    val faker = Faker()
    return try {
        val response = ResponseWrapper(
            success = false,
            message = faker.random.randomString(length = 10),
            data = data,
        )
        Json.encodeToString(response)
    } catch (_: Exception) {
        EMPTY_STRING
    }
}
