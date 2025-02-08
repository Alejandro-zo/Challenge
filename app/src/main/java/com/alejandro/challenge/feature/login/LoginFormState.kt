package com.alejandro.challenge.feature.login

import com.alejandro.domain.util.Constants.EMPTY_STRING

data class LoginFormState(
    val user: String = EMPTY_STRING,
    val password: String = EMPTY_STRING,
)
