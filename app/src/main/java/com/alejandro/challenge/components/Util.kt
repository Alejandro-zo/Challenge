package com.alejandro.challenge.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    this.clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}

fun Char.isAllowedCharacter(): Boolean {
    return this.code in 32..126 ||
            this.code in 160..255 ||
            this.isLetterOrDigit() || this.isWhitespace() || this == '.'
}