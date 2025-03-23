package com.gonzaloracergalan.portfolio.ui.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircularIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    backgroundColor: Color = Color.Transparent,
    borderColor: Color? = null,
    icon: ImageVector,
    size: Dp = 56.dp
) {
    IconButton(
        onClick = onClick,
        modifier = borderColor?.let { mBorderColor ->
            modifier
                .size(size)
                .background(backgroundColor, shape = CircleShape)
                .border(width = 1.dp, color = mBorderColor, shape = CircleShape)
        } ?: run {
            modifier
                .size(size)
                .background(backgroundColor, shape = CircleShape)
        }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White
        )
    }
}