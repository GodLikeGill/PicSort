package com.godlike.picsort.presentation.gallery

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val infiniteTransition = rememberInfiniteTransition()

    val alpha by infiniteTransition.animateFloat(
        initialValue = 1f, targetValue = 0f, animationSpec = infiniteRepeatable(
            animation = tween(1000), repeatMode = RepeatMode.Reverse
        )
    )


    Column(
        Modifier
            .fillMaxSize()
            .background(color = colors.background)
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        GalleryTopAppBar()
        Row(
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(30.dp))
                .background(color = colors.surfaceContainerLow)
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
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
            Column(
                Modifier.weight(1f)
            ) {
                Row {
                    Text(
                        text = "● ", color = colors.tertiary, modifier = Modifier.alpha(alpha)
                    )
                    Text(
                        text = "Ready to optimize", color = colors.onBackground, fontSize = 12.sp
                    )
                }
                Text(
                    text = "342 Duplicates ● 1.9 GB",
                    color = colors.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
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
                    "Review", color = colors.onPrimary
                )
                Icon(
                    Icons.AutoMirrored.Rounded.ArrowForward,
                    contentDescription = null,
                    tint = colors.onPrimary,
                    modifier = Modifier.size(16.dp),
                )
            }
        }
        Column(
            Modifier
                .fillMaxWidth()
                .aspectRatio(1.5f)
                .clip(RoundedCornerShape(30.dp))
                .background(color = colors.surfaceContainerLowest)
                .padding(2.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.default_image_1),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp))
            )
            Row(
                Modifier.padding(start = 10.dp, end = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Text(
                    text = "Text & Studio",
                    color = colors.onBackground,
                )
                Text(
                    text = "● 3 shots clustered", color = colors.outline, fontSize = 12.sp
                )
                Spacer(Modifier.weight(1f))
                IconButton(
                    onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.MoreHoriz,
                        contentDescription = "",
                        tint = colors.onBackground
                    )
                }
            }
        }
    }
}

@Composable
fun GalleryTopAppBar() {

    val colors = MaterialTheme.colorScheme

    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Row(
            Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.picsort_logo),
                contentDescription = "",
                modifier = Modifier.size(32.dp)
            )
            Text(
                text = "PicSort", color = colors.onSurface
            )
            Text(
                text = "/", color = colors.surfaceVariant
            )
            Text(
                text = "Gallery", color = colors.onSurfaceVariant
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