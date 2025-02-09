package com.alejandro.challenge.feature.productdetail

@Suppress("ConvertObjectToDataObject")
sealed interface UiEvent {
    object ServiceData : UiEvent
    object HideError : UiEvent
    object ResetNavigation : UiEvent
}
