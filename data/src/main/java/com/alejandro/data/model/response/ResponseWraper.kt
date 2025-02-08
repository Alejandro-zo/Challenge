package com.alejandro.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ResponseWrapper<T>(
    @SerialName("success") val success: Boolean? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("data") val data: T? = null,
)