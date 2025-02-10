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
fun DialogUnAuthorize(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
) {
    DialogAlert(
        modifier = modifier
            .padding(8.dp),
        onDismissDefault = false,
        icon = R.drawable.oc_time_flies,
        message = stringResource(R.string.un_authorize_message),
        title = stringResource(R.string.un_authorize_error),
        onDismiss = onDismiss,
        onClickButton = onDismiss,
    )
}

@Composable
@Preview
private fun PreviewDialogErrorNetwork() {
    PreviewComponent {
        DialogUnAuthorize(
            onDismiss = {},
        )
    }
}
