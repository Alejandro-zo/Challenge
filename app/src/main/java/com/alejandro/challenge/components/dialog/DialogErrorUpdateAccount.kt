package com.alejandro.challenge.components.dialog

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alejandro.challenge.R
import com.alejandro.challenge.components.PreviewComponent

@Composable
fun DialogErrorUpdateAccount(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
) {
    DialogAlert(
        modifier = modifier.padding(8.dp),
        onDismissDefault = false,
        textButton = stringResource(R.string.accept),
        icon = R.drawable.ic_no_service,
        message = stringResource(R.string.try_again),
        onDismiss = onDismiss,
        onClickButton = onDismiss
    )
}

@Composable
@Preview
private fun PreviewDialogErrorNetwork() {
    PreviewComponent {
        DialogErrorUpdateAccount(
            onDismiss = {},
        )
    }
}
