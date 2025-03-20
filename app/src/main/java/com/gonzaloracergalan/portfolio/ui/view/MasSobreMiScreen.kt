package com.gonzaloracergalan.portfolio.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gonzaloracergalan.portfolio.ui.viewmodel.MasSobreMiViewModel

@Composable
fun MasSobreMiScreen(
    paddingValues: PaddingValues
) {
    val viewModel: MasSobreMiViewModel = viewModel<MasSobreMiViewModel>()
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Red)
    )
}