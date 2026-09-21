package com.godlike.picsort.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

object Spacing {
    val xs = 4.dp
    val sm = 8.dp
    val md = 16.dp
    val lg = 24.dp
    val xl = 40.dp
}

@Composable
fun PicSortTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = PicSortDarkColorScheme,
        content = content,
    )
}