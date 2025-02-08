package com.alejandro.challenge.components.input

import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.alejandro.challenge.R
import com.alejandro.challenge.components.PreviewComponent
import com.alejandro.challenge.components.icon.IconPainterNoClickable

@Composable
fun TexFieldPassword(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = stringResource(R.string.password),
    error: String? = null,
    placeholder: String = stringResource(R.string.enter_your_password),
) {
    var viewPassword by remember { mutableStateOf(false) }
    var visual by remember { mutableStateOf<VisualTransformation>(PasswordVisualTransformation()) }
    var icon by remember { mutableIntStateOf(R.drawable.ic_view_off) }

    LaunchedEffect(viewPassword) {
        icon = if (viewPassword) R.drawable.ic_view else R.drawable.ic_view_off
        visual = if (viewPassword) VisualTransformation.None else PasswordVisualTransformation()
    }

    TexFieldBase(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = label,
        trailingIcon = {
            IconButton(
                onClick = { viewPassword = !viewPassword },
                content = { IconPainterNoClickable(painterResource(icon)) }
            )
        },
        error = error,
        placeholder = placeholder,
        visualTransformation = visual,
    )
}

@Preview
@Composable
private fun PreviewTexFieldPassword() {
    PreviewComponent { TexFieldPassword("", {}) }
}
