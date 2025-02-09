package com.alejandro.challenge.components.icon

import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import com.alejandro.challenge.R

@Composable
fun IconPainterNoClickable(
    painter: Painter,
    modifier: Modifier = Modifier,
    description: String = stringResource(R.string.icon_painter_no_ripple_clickable_content_description),
    tint: Color = LocalContentColor.current,
) {
    Icon(
        painter = painter,
        contentDescription = description,
        modifier = modifier,
        tint = tint,
    )
}
