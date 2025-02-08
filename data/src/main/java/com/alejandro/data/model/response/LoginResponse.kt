package com.alejandro.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class LoginResponse(
    @SerialName("accessToken") val accessToken: String?,
)
