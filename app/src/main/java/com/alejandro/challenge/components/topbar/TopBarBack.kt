package com.alejandro.challenge.components.topbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alejandro.challenge.components.PreviewComponent
import com.alejandro.challenge.components.icon.IconImageVectorButton
import com.alejandro.challenge.components.text.Text16Medium

@Composable
fun TopBarBack(
    title: String,
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit = {},
) {
    var clickBack by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(clickBack) { if (clickBack) onClickBack() }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 48.dp, bottom = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        IconImageVectorButton(
            imageVector = Icons.Rounded.ArrowBackIosNew,
            onClick = { clickBack = true },
        )

        Text16Medium(
            text = title,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(end = 48.dp)
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun PreviewTopBarTitle() {
    PreviewComponent { TopBarBack(title = "Home") {} }
}
