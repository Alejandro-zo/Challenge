package com.alejandro.challenge.feature.home

import com.alejandro.domain.entity.Account

@Suppress("ConvertObjectToDataObject")
sealed interface UiEvent {
    object ServiceData : UiEvent
    object OnRefresh : UiEvent
    class ClickAccount(val account: Account) : UiEvent
    object HideError : UiEvent
    object Retry : UiEvent
    object ResetNavigation : UiEvent
}
