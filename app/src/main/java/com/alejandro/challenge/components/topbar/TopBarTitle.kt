package com.alejandro.challenge.components.topbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alejandro.challenge.components.PreviewComponent
import com.alejandro.challenge.components.text.Text16Medium

@Composable
fun TopBarTitle(
    title: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 48.dp, bottom = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Text16Medium(text = title,)
    }
}

@Preview
@Composable
private fun PreviewTopBarTitle() {
    PreviewComponent { TopBarTitle(title = "Home") }
}
