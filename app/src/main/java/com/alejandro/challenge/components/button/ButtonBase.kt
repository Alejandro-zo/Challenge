package com.alejandro.challenge.components.button

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alejandro.challenge.components.PreviewComponent
import com.alejandro.challenge.components.spacer.Spacer12

@Composable
fun ButtonBase(
    modifier: Modifier = Modifier,
    enabled: Boolean = false,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        contentPadding = PaddingValues(vertical = 12.dp),
        colors = ButtonDefaults.buttonColors().copy(
            disabledContainerColor = MaterialTheme.colorScheme.outlineVariant,
        ),
        enabled = enabled,
        shape = RoundedCornerShape(100.dp),
        onClick = onClick,
    ) {
        content()
    }
}


@Composable
fun ButtonWrapContent(
    modifier: Modifier = Modifier,
    enabled: Boolean = false,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Button(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 12.dp, horizontal = 16.dp),
        enabled = enabled,
        shape = RoundedCornerShape(100.dp),
        onClick = onClick,
    ) {
        content()
    }
}

@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun PreviewButtonBase() {
    PreviewComponent {
        Column {
            ButtonBase(
                enabled = false,
                onClick = {},
                content = {}
            )

            Spacer12()

            ButtonWrapContent(
                enabled = true,
                onClick = {},
                content = {}
            )
        }
    }
}
