package com.example.pruebatecnicafrogtek.ui.views.composables.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.pruebatecnicafrogtek.ui.theme.Dimens.Dp1
import com.example.pruebatecnicafrogtek.ui.theme.Dimens.Dp150
import com.example.pruebatecnicafrogtek.ui.theme.Dimens.Dp180
import com.example.pruebatecnicafrogtek.ui.theme.Dimens.Dp4
import com.example.pruebatecnicafrogtek.ui.theme.Dimens.Dp8
import com.example.pruebatecnicafrogtek.ui.theme.gray8Color
import com.example.pruebatecnicafrogtek.ui.theme.gray9Color

@Composable
private fun SkeletonEffect(
    modifier: Modifier = Modifier
) {

    val shimmerColors = listOf(
        gray8Color,
        gray9Color
    )

    val transition = rememberInfiniteTransition(label = "")

    val animatedColor by transition.animateColor(
        initialValue = shimmerColors[0],
        targetValue = shimmerColors[1],
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, delayMillis = 200),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Box(
        modifier = modifier.background(animatedColor)
    )
}

@Composable
fun CoilImage(imageUri: String, modifier: Modifier) {
    SubcomposeAsyncImage(
        model = imageUri,
        contentDescription = "",
        contentScale = ContentScale.Inside,
        modifier = modifier
    ) {
        val state = painter.state
        when (state) {
            AsyncImagePainter.State.Empty -> Unit
            is AsyncImagePainter.State.Error -> Unit
            is AsyncImagePainter.State.Loading -> Unit
            is AsyncImagePainter.State.Success -> {
                SubcomposeAsyncImageContent()
            }
        }
    }
}

@Composable
fun Skeleton() {
    Card(
        modifier = Modifier
            .height(Dp180)
            .width(Dp150)
            .padding(vertical = Dp8, horizontal = Dp4)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            SkeletonEffect(
                modifier =
                    Modifier
                        .weight(1f)
                        .fillMaxWidth()
            )
            HorizontalDivider(
                modifier = Modifier
                    .height(Dp1)
                    .fillMaxWidth()
                    .background(Color.Black)
            )
            SkeletonEffect(
                modifier =
                    Modifier
                        .weight(1f)
                        .fillMaxWidth()
            )
        }
    }
}