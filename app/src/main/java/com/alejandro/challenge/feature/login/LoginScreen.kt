package com.alejandro.challenge.feature.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alejandro.challenge.R
import com.alejandro.challenge.components.PreviewComponent
import com.alejandro.challenge.components.basescreen.BaseScreen
import com.alejandro.challenge.components.button.ButtonText
import com.alejandro.challenge.components.error.ErrorHandle
import com.alejandro.challenge.components.input.TexFieldBase
import com.alejandro.challenge.components.input.TexFieldPassword
import com.alejandro.challenge.components.loading.Loading
import com.alejandro.challenge.components.spacer.Spacer24

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToHome: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val formState by viewModel.formState.collectAsStateWithLifecycle()
    var showError by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.handleUiEvent(UiEvent.InsertData)
    }

    LoginScreenContent(
        formState = formState,
        uiState = uiState,
        sendUiEvent = viewModel::handleUiEvent
    )

    LaunchedEffect(uiState.navigateToHome) {
        if (uiState.navigateToHome) navigateToHome()
    }

    LaunchedEffect(uiState.error) {
        showError = (uiState.error != null)
    }

    if (showError) {
        uiState.error?.let {
            ErrorHandle(
                error = it,
                onDismiss = { viewModel.handleUiEvent(UiEvent.HideError) },
                onUnAuthorize = { viewModel.handleUiEvent(UiEvent.HideError) },
            )
        }
    }

    if (uiState.isLoading) {
        Loading()
    }
}

@Composable
private fun LoginScreenContent(
    formState: LoginFormState,
    uiState: UiState,
    sendUiEvent: (UiEvent) -> Unit,
) {

    BaseScreen(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
    ) {

        Image(
            painter = painterResource(R.drawable.ic_logo_and_letters),
            contentDescription = stringResource(R.string.logo_interbank),
        )

        Column {

            TexFieldBase(
                value = formState.user,
                onValueChange = { sendUiEvent(UiEvent.UserChanged(it)) },
                label = stringResource(R.string.username),
                placeholder = stringResource(R.string.enter_your_username),
            )

            Spacer24()

            TexFieldPassword(
                value = formState.password,
                onValueChange = { sendUiEvent(UiEvent.PasswordChanged(it)) },
            )

            Spacer24()

            ButtonText(
                text = stringResource(R.string.enter),
                enabled = uiState.enableButton,
                onClick = { sendUiEvent(UiEvent.ButtonClickedEnter) },
            )
        }
    }
}

@Preview
@Composable
private fun LoginScreenContentPreview() {
    PreviewComponent {
        LoginScreenContent(
            uiState = UiState(),
            formState = LoginFormState(),
            sendUiEvent = {}
        )
    }
}
