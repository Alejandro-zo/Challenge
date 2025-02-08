package com.alejandro.challenge.feature.login

@Suppress("ConvertObjectToDataObject")
sealed interface UiEvent {
    object InsertData : UiEvent
    class UserChanged(val value: String) : UiEvent
    class PasswordChanged(val value: String) : UiEvent
    object ButtonClickedEnter : UiEvent
    object HideError : UiEvent
}
