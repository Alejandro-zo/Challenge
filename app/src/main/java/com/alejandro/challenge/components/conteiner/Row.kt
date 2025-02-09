package com.alejandro.challenge.components.conteiner

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun RowAlignedLeftRight(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(),
    contentLeft: @Composable () -> Unit,
    contentRight: @Composable () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(paddingValues)
    ) {
        Column(modifier = Modifier.weight(1f)) { contentLeft() }
        Column { contentRight() }
    }
}
