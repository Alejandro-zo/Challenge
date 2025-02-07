package com.alejandro.challenge.components.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun Text16(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = 24.sp,
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = 16.sp,
        color = color,
        textDecoration = textDecoration,
        textAlign = textAlign,
        style = TextStyle(lineHeight = lineHeight),
    )
}

@Composable
fun Text16Medium(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = 24.sp,
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        color = color,
        textDecoration = textDecoration,
        textAlign = textAlign,
        style = TextStyle(lineHeight = lineHeight),
    )
}

@Composable
fun Text16SemiBold(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = 24.sp,
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        color = color,
        textDecoration = textDecoration,
        textAlign = textAlign,
        style = TextStyle(lineHeight = lineHeight),
    )
}

@Composable
fun Text16Bold(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = 24.sp,
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = 16.sp,
        color = color,
        fontWeight = FontWeight.Bold,
        textDecoration = textDecoration,
        textAlign = textAlign,
        style = TextStyle(lineHeight = lineHeight),
    )
}
