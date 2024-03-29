package com.app.compose.presentation.components.shimmer

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerLoading(
    imageHeight: Dp,
    padding: Dp = 16.dp,
    shimmerCount: Int = 5,
    underlines: Int = 1
) {
    BoxWithConstraints {
        val cardWidthPx = with(LocalDensity.current) {
            ((maxWidth - (padding * 2)).toPx())
        }
        val cardHeightPx = with(LocalDensity.current) {
            ((imageHeight - (padding)).toPx())
        }
        val gradientWidth = (0.2f * cardHeightPx)
        val infiniteTransition = rememberInfiniteTransition()
        val xCardShimmer = infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = (cardWidthPx + gradientWidth),
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 1300,
                    easing = LinearEasing,
                    delayMillis = 300
                ),
                repeatMode = RepeatMode.Restart
            )
        )
        val yCardShimmer = infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = (cardHeightPx + gradientWidth),
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 1300,
                    easing = LinearEasing,
                    delayMillis = 300
                ),
                repeatMode = RepeatMode.Restart
            )
        )
        val colors = listOf(
            Color.LightGray.copy(alpha = 0.9f),
            Color.LightGray.copy(alpha = 0.9f),
            Color.LightGray.copy(alpha = 1f)
        )
        LazyColumn{
            items(shimmerCount) {
                ShimmerRecipeCardItem(
                    colors = colors,
                    cardHeight = 250.dp,
                    xShimmer = xCardShimmer.value,
                    yShimmer = yCardShimmer.value,
                    padding = padding,
                    gradientWidth = gradientWidth,
                    underlines = underlines
                )
            }
        }

    }
}