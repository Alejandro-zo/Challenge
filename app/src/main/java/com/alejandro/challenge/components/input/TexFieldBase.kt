package com.alejandro.challenge.components.input

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alejandro.challenge.components.PreviewComponent
import com.alejandro.challenge.components.isAllowedCharacter
import com.alejandro.challenge.components.spacer.Spacer8
import com.alejandro.challenge.components.text.Text14
import com.alejandro.challenge.theme.grayPrincipal
import com.alejandro.domain.util.Constants.EMPTY_STRING

@Composable
fun TexFieldBase(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = EMPTY_STRING,
    error: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    placeholder: String = EMPTY_STRING,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
) {

    Column(modifier = modifier) {
        if (label.isNotBlank()) {
            Text14(text = label)

            Spacer8()
        }

        OutlinedTextField(
            value = value,
            onValueChange = { input ->
                onValueChange(input.filter { it.isAllowedCharacter() })
            },
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { },
            enabled = enabled,
            readOnly = readOnly,
            textStyle = textStyle,
            placeholder = { Text14(text = placeholder, color = grayPrincipal) },
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            prefix = prefix,
            suffix = suffix,
            isError = !error.isNullOrBlank(),
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            shape = RoundedCornerShape(8.dp),
            interactionSource = interactionSource,
        )
    }
}

@Preview
@Composable
private fun PreviewTexFieldBase() {
    PreviewComponent {
        TexFieldBase(
            value = EMPTY_STRING,
            onValueChange = {}
        )
    }
}
