package com.godlike.picsort.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.godlike.picsort.R
import com.godlike.picsort.ui.theme.PicSortTheme
import com.godlike.picsort.ui.theme.Spacing

@Composable
fun GalleryScreen() {
    GalleryScreenContent()
}

@Composable
fun GalleryScreenContent() {

    val colors = MaterialTheme.colorScheme

    Column(
        Modifier
            .fillMaxSize()
            .background(color = colors.background)
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        GalleryTopAppBar()
        Row (
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(30.dp))
                .background(color = colors.surfaceContainerLow)
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.AutoAwesome,
                contentDescription = "",
                tint = colors.primary,
                modifier = Modifier
                    .background(
                        shape = CircleShape,
                        color = colors.surfaceContainerHigh,
                    )
                    .padding(10.dp)
            )
            Text(
                text = "342 Duplicates ● 1.9 GB",
                color = colors.onSurface
            )
            Row(
                Modifier
                    .heightIn(min = 40.dp)
                    .shadow(
                        elevation = 12.dp,
                        shape = CircleShape,
                        ambientColor = colors.primaryContainer.copy(alpha = 0.28f),
                        spotColor = colors.primaryContainer.copy(alpha = 0.28f),
                    )
                    .background(colors.primaryContainer)
                    .clickable(role = Role.Button, onClick = {})
                    .padding(horizontal = Spacing.md, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    "Review",
                    color = colors.onPrimary
                )
                Icon(
                    Icons.AutoMirrored.Rounded.ArrowForward,
                    contentDescription = null,
                    tint = colors.onPrimary,
                    modifier = Modifier.size(16.dp),
                )
            }
        }
        Box(
            Modifier
                .fillMaxWidth()
                .aspectRatio(1.5f)
                .clip(RoundedCornerShape(30.dp))
        ) {
            Image(painter = painterResource(R.drawable.default_image_1), contentDescription = "")
        }
    }
}

@Composable
fun GalleryTopAppBar() {

    val colors = MaterialTheme.colorScheme

    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Row(
            Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(R.drawable.picsort_logo),
                contentDescription = "",
                modifier = Modifier.size(32.dp)
            )
            Text(
                text = "PicSort",
                color = colors.onSurface
            )
            Text(
                text = "/",
                color = colors.surfaceVariant
            )
            Text(
                text = "Gallery",
                color = colors.onSurfaceVariant
            )
        }
        Box(
            Modifier
                .size(32.dp)
                .clip(CircleShape)
                .clickable(onClick = {}),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "",
                tint = Color.White
            )
        }
    }
}

@Preview
@Composable
fun PreviewGalleryTopAppBar() {
    PicSortTheme { GalleryTopAppBar() }

}

@Preview
@Composable
fun PreviewGalleryScreen() {
    PicSortTheme { GalleryScreen() }
}