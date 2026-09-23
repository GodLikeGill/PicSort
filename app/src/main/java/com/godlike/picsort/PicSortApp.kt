package com.godlike.picsort

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.PhotoLibrary
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.godlike.picsort.presentation.BottomNavItem
import com.godlike.picsort.presentation.gallery.GalleryScreen
import com.godlike.picsort.presentation.PicSortBottomNav
import com.godlike.picsort.presentation.cleanup.CleanupScreen
import com.godlike.picsort.presentation.explore.ExploreScreen

object Routes {
    const val GALLERY = "gallery"
    const val CLEANUP = "cleanup"
    const val EXPLORE = "explore"
}

@Composable
fun PicSortApp() {

    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination

    val items = listOf(
        BottomNavItem(Routes.GALLERY, "Gallery", Icons.Outlined.PhotoLibrary),
        BottomNavItem(Routes.CLEANUP, "Cleanup", Icons.Outlined.AutoAwesome, badgeCount = 342),
        BottomNavItem(Routes.EXPLORE, "Explore", Icons.Outlined.Explore),
    )

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            PicSortBottomNav(
                items = items,
                selectedRoute = items.firstOrNull { item -> currentDestination?.hierarchy?.any { it.route == item.route } == true }?.route,
                onItemClick = { item ->
                    navController.navigate(item.route) {
                        // Pop back to the start so the back stack doesn't grow every time you tap tabs…
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        // …tapping the current tab again doesn't stack duplicates…
                        launchSingleTop = true
                        // …and returning to a tab restores its scroll position / state.
                        restoreState = true
                    }
                },
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.GALLERY,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.GALLERY) { GalleryScreen() }
            composable(Routes.CLEANUP) { CleanupScreen() }
            composable(Routes.EXPLORE) { ExploreScreen() }
        }
    }
}