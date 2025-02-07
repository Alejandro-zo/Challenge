package com.alejandro.challenge.components.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.alejandro.challenge.components.PreviewComponent
import com.alejandro.data.BuildConfig

@Composable
fun DialogBase(
    modifier: Modifier = Modifier,
    onDismissDefault: Boolean = true,
    paddingValues: PaddingValues = PaddingValues(horizontal = 16.dp),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    onDismiss: () -> Unit,
    content: @Composable () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = onDismissDefault,
            dismissOnClickOutside = onDismissDefault,
            usePlatformDefaultWidth = false,
        )
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.onPrimary
        ) {
            Column(
                modifier = modifier.padding(16.dp),
                verticalArrangement = verticalArrangement,
                horizontalAlignment = horizontalAlignment,
            ) {
                content()
            }
        }
    }
}

@Preview
@Composable
private fun DialogBasePreview() {
    PreviewComponent {
        DialogBase(
            onDismiss = {},
            content = { Text(BuildConfig.LIBRARY_PACKAGE_NAME) }
        )
    }
}

