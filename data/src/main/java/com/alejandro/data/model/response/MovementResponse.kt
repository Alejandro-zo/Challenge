package com.alejandro.data.model.response

import com.alejandro.domain.entity.Movement
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class MovementResponse(
    @SerialName("accountNumber") val accountNumber: String?,
    @SerialName("sing") val sing: String?,
    @SerialName("amount") val amount: Double?,
    @SerialName("concept") val concept: String?,
    @SerialName("date") val date: String?,
)

fun MovementResponse.toDomain(): Movement {
    return Movement(
        accountNumber = accountNumber.orEmpty(),
        sing = sing.orEmpty(),
        amount = amount ?: 0.00,
        concept = concept.orEmpty(),
        date = date.orEmpty(),
    )
}

fun List<MovementResponse>.toDomain() = this.map { it.toDomain() }
