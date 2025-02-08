package com.alejandro.challenge.components.error

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.alejandro.challenge.R
import com.alejandro.challenge.components.PreviewComponent
import com.alejandro.challenge.components.dialog.DialogAlert
import com.alejandro.domain.util.GenericException
import com.alejandro.domain.util.UnAuthorizeException

@Composable
fun ErrorHandle(
    error: Throwable,
    onDismiss: () -> Unit = {},
    onUnAuthorize: () -> Unit = {},
) {

    when (error) {
        is UnAuthorizeException -> {
            DialogAlert(
                onClickButton = onUnAuthorize,
                title = stringResource(R.string.un_authorize_error),
                message = stringResource(R.string.un_authorize_error_message)
            )
        }

        else -> {
            DialogAlert(
                onClickButton = onDismiss,
                message = error.message ?: stringResource(R.string.generic_error_message)
            )
        }
    }
}

@Composable
@Preview
private fun PreviewErrorHandle() {
    PreviewComponent {
        ErrorHandle(
            error = GenericException(),
            onDismiss = {},
            onUnAuthorize = {},
        )
    }
}
