package com.alejandro.challenge.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.alejandro.challenge.theme.ChallengeTheme

@Composable
fun PreviewComponent(content: @Composable () -> Unit) {
    MaterialTheme {
        ChallengeTheme {
            Surface {
                content()
            }
        }
    }
}
