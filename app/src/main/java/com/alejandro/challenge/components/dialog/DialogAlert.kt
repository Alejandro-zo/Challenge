package com.alejandro.challenge.components.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alejandro.challenge.R
import com.alejandro.challenge.components.PreviewComponent
import com.alejandro.challenge.components.button.ButtonText
import com.alejandro.challenge.components.spacer.Spacer24
import com.alejandro.challenge.components.spacer.Spacer8
import com.alejandro.challenge.components.text.Text16Medium
import com.alejandro.challenge.components.text.Text20SemiBold
import com.alejandro.domain.util.Constants.LOGIN_ERROR_MESSAGE

@Composable
fun DialogAlert(
    modifier: Modifier = Modifier,
    icon: Int = R.drawable.ic_no_service,
    onDismissDefault: Boolean = false,
    message: String? = null,
    textButton: String = stringResource(R.string.accept),
    title: String? = null,
    onDismiss: () -> Unit = {},
    onClickButton: () -> Unit = {},
) {
    DialogBase(
        modifier = modifier,
        paddingValues = PaddingValues(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        onDismiss = onDismiss,
        onDismissDefault = onDismissDefault,
    ) {

        Image(
            painter = painterResource(icon),
            contentDescription = "",
            modifier = modifier,
        )

        Spacer24()

        title?.let {
            Text20SemiBold(text = title, textAlign = TextAlign.Center)

            Spacer8()
        }

        message?.let {
            Text16Medium(text = message, textAlign = TextAlign.Center)
        }

        Spacer24()

        ButtonText(text = textButton) { onClickButton() }
    }
}

@Composable
@Preview
private fun BaseDialogPreview() {
    PreviewComponent {
        DialogAlert(
            icon = R.drawable.ic_no_service,
            message = LOGIN_ERROR_MESSAGE,
            onDismiss = {},
        )
    }
}
