package com.godlike.picsort

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.godlike.picsort.ui.theme.PicSortTheme

class PicSortActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PicSortTheme {
                PicSortApp()
            }
        }
    }
}