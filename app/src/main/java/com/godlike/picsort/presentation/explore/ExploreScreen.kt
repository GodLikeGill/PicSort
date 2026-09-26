package com.godlike.picsort.presentation.explore

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AutoFixHigh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.PhotoLibrary
import androidx.compose.material.icons.rounded.Mic
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.godlike.picsort.R
import com.godlike.picsort.ui.theme.PicSortTheme

val suggestedQueries = listOf(
    "Sunset in Bug Sun",
    "Whiteboard notes",
    "Identifications",
    "Troublesome words",
    "Anything at this point"
)

data class Category(
    val title: String, val subtitle: String, val imageRes: Int, val isLocked: Boolean = false
)

val sampleCategories = listOf(
    Category("Architecture", "1,410 photos", R.drawable.default_image_1, isLocked = true),
    Category("Nature", "930 photos", R.drawable.default_image_2, isLocked = true),
    Category("Sunsets", "624 photos", R.drawable.default_image_3, isLocked = true),
    Category("Food & Dining", "418 photos", R.drawable.default_image_1, isLocked = true),
    Category("Documents", "352 scans", R.drawable.default_image_2, isLocked = true),
    Category("Travel", "1,180 photos", R.drawable.default_image_3, isLocked = true)
)

@Composable
fun ExploreScreen() {
    ExploreScreenContent(onVoiceSearch = {})
}

@Composable
fun ExploreScreenContent(
    onVoiceSearch: () -> Unit
) {

    val colors = MaterialTheme.colorScheme
    var query = ""

    Column(
        Modifier
            .fillMaxSize()
            .background(color = colors.background)
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        ExploreTopAppBar()

        Row(
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(30.dp))
                .background(color = colors.surfaceContainerLow)
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Search, contentDescription = "", tint = colors.primary
            )
            BasicTextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier
                    .weight(1f)
                    .background(
                        colors.surfaceContainerLow, shape = RoundedCornerShape(16.dp)
                    ),
                textStyle = TextStyle(
                    color = colors.onBackground,
                    fontSize = 16.sp,
                ),
                cursorBrush = SolidColor(colors.primary),
                singleLine = true,
                decorationBox = {
                    Box(
                        contentAlignment = Alignment.CenterStart,
                    ) {
                        if (query.isEmpty()) {
                            Text(
                                text = "Search with AI (e.g., 'golden hour')",
                                color = colors.outline
                            )
                        }
                    }
                })
            IconButton(
                onClick = onVoiceSearch, modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Mic,
                    contentDescription = "Voice Search",
                    tint = colors.primary,
                    modifier = Modifier.size(22.dp)
                )
            }
        }

        Column(
            Modifier.fillMaxWidth(),
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Text(
                    text = "SUGGESTED QUERIES",
                    color = colors.outline,
                    fontSize = 13.sp,
                    modifier = Modifier.weight(1f)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.AutoFixHigh,
                        contentDescription = "",
                        tint = colors.tertiary,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Semantic AI", fontSize = 13.sp, color = colors.tertiary
                    )
                }
            }
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                items(suggestedQueries) { q ->
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(30.dp))
                            .background(color = colors.surfaceContainerLow)
                            .padding(horizontal = 10.dp, vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(3.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.PhotoLibrary,
                            contentDescription = "",
                            tint = colors.tertiary,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = q,
                            fontSize = 12.sp,
                            color = colors.onBackground,
                        )
                    }
                }
            }
        }
        Column(
            Modifier.fillMaxSize()
        ) {
            Row(
                Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.Bottom, modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Categories ",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = colors.onBackground
                    )
                    Text(
                        text = "(6 Collections)", fontSize = 10.sp, color = colors.outline
                    )
                }
                Text(
                    text = "Auto-Indexed", fontSize = 12.5.sp, color = colors.onSurfaceVariant
                )
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(sampleCategories) { category ->
                    CategoryCard(category = category)
                }
            }
        }
    }
}

@Composable
fun ExploreTopAppBar() {

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

@Composable
fun CategoryCard(category: Category) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.85f) // Slightly taller than wide
            .clip(RoundedCornerShape(16.dp))
    ) {
        // 1. Background Image
        Image(
            painter = painterResource(id = category.imageRes),
            contentDescription = category.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // 2. Dark Gradient Overlay (Crucial for text readability)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.4f),
                            Color.Black.copy(alpha = 0.85f)
                        ), startY = 200f // Adjust to control how far down the gradient starts
                    )
                )
        )

        // 4. Text Information (Bottom Left)
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // The little colored dot
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .background(Color(0xFF8AB4F8), CircleShape)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = category.title,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = category.subtitle, color = Color.LightGray, fontSize = 12.sp
            )
        }
    }
}

@Preview
@Composable
fun PreviewExploreScreenContent() {
    PicSortTheme { ExploreScreenContent(onVoiceSearch = {}) }
}