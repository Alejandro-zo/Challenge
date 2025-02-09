package com.alejandro.challenge.components.conteiner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.PositionalThreshold
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.pullToRefreshIndicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.alejandro.challenge.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullToRefreshBase(
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    val refreshState = rememberPullToRefreshState()
    PullToRefreshBox(
        state = refreshState,
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        indicator = {
            MyCustomIndicator(
                state = refreshState,
                isRefreshing = isRefreshing,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    ) {
        Column { content() }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCustomIndicator(
    state: PullToRefreshState,
    isRefreshing: Boolean,
    modifier: Modifier = Modifier

) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
    val animationState = animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    Box(
        modifier = modifier
            .pullToRefreshIndicator(
                state = state,
                isRefreshing = isRefreshing,
                containerColor = Color.Transparent,
                threshold = PositionalThreshold,
                elevation = 0.dp,
            )
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {

        LottieAnimation(
            composition = composition,
            progress = { animationState.progress },
            modifier = Modifier.size(50.dp),
            contentScale = ContentScale.FillBounds
        )

    }
}
