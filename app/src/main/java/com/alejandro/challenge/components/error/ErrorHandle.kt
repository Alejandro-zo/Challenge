package com.alejandro.challenge.components.error

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.alejandro.challenge.R
import com.alejandro.challenge.components.PreviewComponent
import com.alejandro.challenge.components.dialog.DialogAlert
import com.alejandro.challenge.components.dialog.DialogErrorAccount
import com.alejandro.challenge.components.dialog.DialogErrorNetwork
import com.alejandro.challenge.components.dialog.DialogErrorUpdateAccount
import com.alejandro.domain.util.AccountException
import com.alejandro.domain.util.GenericException
import com.alejandro.domain.util.UnAuthorizeException
import com.alejandro.domain.util.UpdateAccountException
import java.net.UnknownHostException

@Composable
fun ErrorHandle(
    error: Throwable,
    onDismiss: () -> Unit = {},
    onUnAuthorize: () -> Unit = {},
    onRetry: () -> Unit = {},
) {

    when (error) {
        is UnknownHostException -> DialogErrorNetwork(onDismiss = onDismiss)

        is UnAuthorizeException -> {
            DialogAlert(
                onClickButton = onUnAuthorize,
                title = stringResource(R.string.un_authorize_error),
                message = stringResource(R.string.un_authorize_error_message)
            )
        }

        is AccountException ->  DialogErrorAccount(onDismiss = onRetry)

        is UpdateAccountException -> DialogErrorUpdateAccount (onDismiss = onDismiss)

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
