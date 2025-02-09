package com.alejandro.challenge.feature.productdetail

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
import com.alejandro.challenge.components.error.ErrorHandle
import com.alejandro.challenge.components.item.AccountDetail
import com.alejandro.challenge.components.item.ItemMovementCard
import com.alejandro.challenge.components.loading.Loading
import com.alejandro.challenge.components.spacer.Spacer24
import com.alejandro.challenge.components.text.Text14
import com.alejandro.challenge.components.text.Text16SemiBold
import com.alejandro.challenge.components.topbar.TopBarBack

@Composable
fun ProductDetailScreen(
    viewModel: ProductDetailViewModel = hiltViewModel(),
    navigateToLogin: () -> Unit,
    navigateToBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showError by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) { viewModel.handleUiEvent(UiEvent.ServiceData) }

    LaunchedEffect(uiState.error) { showError = (uiState.error != null) }

    ProductDetailContent(
        uiState = uiState,
        navigateToBack = navigateToBack,
    )

    LaunchedEffect(uiState.navigateToLogin) {
        if (uiState.navigateToLogin) navigateToLogin()
    }

    if (uiState.isLoading) {
        Loading()
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
}

@Composable
private fun ProductDetailContent(
    uiState: UiState,
    navigateToBack: () -> Unit,
) {
    BaseScreen(
        topBar = { TopBarBack(stringResource(R.string.queries)) { navigateToBack() } }
    ) {

        uiState.account?.let { AccountDetail(account = it) }

        Spacer24()

        Text16SemiBold(stringResource(R.string.movements))

        Spacer24()

        if (uiState.listMovements.isEmpty()) {
            Text14(stringResource(R.string.you_have_no_movements))
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {

            items(uiState.listMovements) { movement ->

                ItemMovementCard(
                    movement = movement,
                    currency = uiState.account?.currency.orEmpty()
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
        ProductDetailContent(
            uiState = UiState(),
            navigateToBack = {},
        )
    }
}
