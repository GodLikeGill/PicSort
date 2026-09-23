package com.godlike.picsort.presentation

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.PhotoLibrary
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.godlike.picsort.ui.theme.PicSortTheme

@Composable
fun PicSortBottomNav(
    items: List<BottomNavItem>,
    selectedRoute: String?,
    onItemClick: (BottomNavItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme

    Box(
        modifier
            .background(colors.surfaceContainerLow.copy(alpha = 0.90f))
            .fillMaxWidth()
            .navigationBarsPadding()
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 16.dp,
                    shape = CircleShape,
                    ambientColor = Color.Black.copy(alpha = 0.45f),
                    spotColor = Color.Black.copy(alpha = 0.45f),
                )
                .background(colors.surfaceContainerLow.copy(alpha = 0.90f))
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            items.forEach { item ->
                PicSortNavItem(
                    item = item,
                    selected = item.route == selectedRoute,
                    onClick = { onItemClick(item) })
            }
        }
    }
}

@Composable
fun PicSortNavItem(
    item: BottomNavItem, selected: Boolean, onClick: () -> Unit
) {
    val colors = MaterialTheme.colorScheme

    // Animate between the two visual states instead of snapping.
    val contentColor by animateColorAsState(
        targetValue = if (selected) colors.primary else colors.onSurfaceVariant,
        label = "navContentColor",
    )
    val containerColor by animateColorAsState(
        targetValue = if (selected) colors.primaryContainer.copy(alpha = 0.15f) else Color.Transparent,
        label = "navContainerColor",
    )

    Column(
        Modifier
            .defaultMinSize(minWidth = 48.dp, minHeight = 48.dp)
            .clip(CircleShape)
            .background(containerColor)
            .selectable(selected = selected, role = Role.Tab, onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        BadgedBox(
            badge = {
                if (item.badgeCount > 0) {
                    Badge(
                        containerColor = colors.secondaryContainer,
                        contentColor = colors.onSecondaryContainer,
                    ) {
                        Text(
                            item.badgeCount.toString(),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            },
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                tint = contentColor,
                modifier = Modifier.size(24.dp),
            )
        }
        Text(
            text = item.label,
            style = MaterialTheme.typography.labelSmall,
            color = contentColor,
            modifier = Modifier.padding(top = 2.dp),
        )
    }
}

@Preview
@Composable
fun PreviewPicSortBottomNav() {
    PicSortTheme {
        PicSortBottomNav(
            items = listOf(
                BottomNavItem("gallery", "Gallery", Icons.Outlined.PhotoLibrary),
                BottomNavItem("cleanup", "Cleanup", Icons.Outlined.AutoAwesome, badgeCount = 342),
                BottomNavItem("explore", "Explore", Icons.Outlined.Explore),
            ),
            selectedRoute = "gallery",
            onItemClick = {},
        )
    }
}