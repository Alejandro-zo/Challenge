package com.alejandro.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class LoginRequest(
    @SerialName("userName") val userName: String,
    @SerialName("password") val password: String,
)
