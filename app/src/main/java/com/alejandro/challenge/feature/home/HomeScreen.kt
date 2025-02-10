package com.alejandro.challenge.feature.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alejandro.challenge.R
import com.alejandro.challenge.components.PreviewComponent
import com.alejandro.challenge.components.basescreen.BaseScreen
import com.alejandro.challenge.components.conteiner.PullToRefreshBase
import com.alejandro.challenge.components.error.ErrorHandle
import com.alejandro.challenge.components.item.ItemAccountCard
import com.alejandro.challenge.components.item.ItemAccountError
import com.alejandro.challenge.components.loading.Loading
import com.alejandro.challenge.components.spacer.Spacer24
import com.alejandro.challenge.components.topbar.TopBarTitle
import com.alejandro.challenge.main.SessionViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    sessionViewModel: SessionViewModel = hiltViewModel(),
    navigateToAccountDetail: (String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showError by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) { viewModel.handleUiEvent(UiEvent.ServiceData) }
    LaunchedEffect(Unit) { sessionViewModel.updateLastActivity() }

    LaunchedEffect(uiState.error) { showError = (uiState.error != null) }

    PullToRefreshBase(
        isRefreshing = uiState.isRefreshing,
        onRefresh = { viewModel.handleUiEvent(UiEvent.OnRefresh) }
    ) {
        HomeScreenContent(
            uiState = uiState,
            sendUiEvent = viewModel::handleUiEvent
        )
    }

    if (uiState.isLoading) {
        Loading()
    }

    if (uiState.navigateToAccountDetail) {
        navigateToAccountDetail(uiState.accountNumber)
        viewModel.handleUiEvent(UiEvent.ResetNavigation)
    }

    if (showError) {
        uiState.error?.let {
            ErrorHandle(
                error = it,
                onDismiss = { viewModel.handleUiEvent(UiEvent.HideError) },
                onUnAuthorize = { viewModel.handleUiEvent(UiEvent.HideError) },
                onRetry = { viewModel.handleUiEvent(UiEvent.Retry) },
            )
        }
    }
}

@Composable
private fun HomeScreenContent(
    uiState: UiState,
    sendUiEvent: (UiEvent) -> Unit,
) {
    BaseScreen(
        topBar = { TopBarTitle(stringResource(R.string.product)) }
    ) {

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            if (uiState.errorData) {
                item {
                    ItemAccountError(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        message = stringResource(uiState.messageErrorData)
                    )
                }
            }

            items(uiState.listAccount) { account ->

                ItemAccountCard(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    account = account,
                    onClickAccount = { sendUiEvent(UiEvent.ClickAccount(account)) }
                )

                Spacer24()
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenContentPreview() {
    PreviewComponent {
        HomeScreenContent(
            uiState = UiState(),
            sendUiEvent = {},
        )
    }
}
