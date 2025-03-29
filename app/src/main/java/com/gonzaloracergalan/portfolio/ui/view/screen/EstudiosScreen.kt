package com.gonzaloracergalan.portfolio.ui.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gonzaloracergalan.portfolio.ui.viewmodel.EstudiosViewModel

@Composable
fun EstudiosScreen(
    paddingValues: PaddingValues
) {
    val viewModel: EstudiosViewModel = viewModel<EstudiosViewModel>()
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Green)
    )
}