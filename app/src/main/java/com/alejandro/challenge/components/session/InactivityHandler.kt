package com.alejandro.challenge.components.session

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.alejandro.challenge.components.dialog.DialogUnAuthorize
import com.alejandro.challenge.main.SessionViewModel

@Composable
fun InactivityHandle(
    sessionViewModel: SessionViewModel = hiltViewModel(),
    onInactivity: (() -> Unit)? = null,
) {
    val isSessionExpired by sessionViewModel.isSessionExpired.collectAsState()
    var inactivity by remember { mutableStateOf(false) }

    LaunchedEffect(isSessionExpired) { inactivity = isSessionExpired }

    if (inactivity) {
        DialogUnAuthorize {
            onInactivity?.invoke()
            inactivity = false
        }
    }
}
