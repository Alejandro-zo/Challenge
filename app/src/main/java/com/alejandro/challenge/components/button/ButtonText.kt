package com.alejandro.challenge.components.button

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alejandro.challenge.components.PreviewComponent
import com.alejandro.challenge.components.text.Text16Medium

@Composable
fun ButtonText(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,

    ) {
    ButtonBase(
        modifier = modifier,
        enabled = enabled,
        onClick = { onClick() }
    ) {
        Text16Medium(text)
    }
}

@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun ButtonTextPreview() {
    PreviewComponent {
        ButtonText(
            text = "Value",
            onClick = {}
        )
    }
}
