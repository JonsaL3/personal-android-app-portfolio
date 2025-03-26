package com.gonzaloracergalan.portfolio.ui.view.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.gonzaloracergalan.portfolio.R

@Composable
fun BoxWithBackgroundAnimatedImage(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) { // todo Blur y desplazamiento de la imagen de fondo
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.img_background_example), // todo imagen dinamica
            contentDescription = "background",
            contentScale = ContentScale.Crop,
            alignment = Alignment.CenterStart
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black)
                    )
                )
        )
        content()
    }
}