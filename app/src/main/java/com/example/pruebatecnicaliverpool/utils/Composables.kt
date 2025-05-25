package com.example.pruebatecnicaliverpool.utils

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.pruebatecnicaliverpool.ui.theme.gray8Color
import com.example.pruebatecnicaliverpool.ui.theme.gray9Color


@Composable
fun Skeleton() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            SkeletonEffect(Modifier.size(width = 110.dp, height = 70.dp))
            Column(modifier = Modifier.weight(1f)) {
                SkeletonEffect(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                )
            }
        }
    }
}

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
fun CoilImage(imageUri: String) {
    SubcomposeAsyncImage(
        model = imageUri,
        contentDescription = "",
        contentScale = ContentScale.Inside,
        modifier = Modifier.size(width = 100.dp, height = 80.dp)
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
fun CircleColorVariants(colorHex: String) {
    val color = Color(colorHex.toColorInt())

    Box(
        modifier = Modifier
            .size(20.dp)
            .border(
                width = 0.5.dp,
                color = Color.Black,
                shape = RoundedCornerShape(16.dp)
            )
            .background(
                color = color,
                shape = RoundedCornerShape(16.dp)
            )
    )
}