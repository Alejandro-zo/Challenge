package com.alejandro.challenge.components.icon

import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.alejandro.challenge.R
import com.alejandro.challenge.components.noRippleClickable

@Composable
fun IconImageVector(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    description: String = stringResource(R.string.icon_image_vector_content_description),
    tint: Color = LocalContentColor.current,
    onClick: (() -> Unit)? = null
) {
    Icon(
        imageVector = imageVector,
        contentDescription = description,
        modifier = modifier.clickable { onClick?.invoke() },
        tint = tint,
    )
}

@Composable
fun IconImageVectorNoRippleClickable(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    description: String = stringResource(R.string.icon_image_vector_no_ripple_clickable_content_description),
    tint: Color = LocalContentColor.current,
    onClick: (() -> Unit)? = null
) {
    Icon(
        imageVector = imageVector,
        contentDescription = description,
        modifier = modifier
            .noRippleClickable { onClick?.invoke() },
        tint = tint,
    )
}

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
