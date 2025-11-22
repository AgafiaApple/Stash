package com.example.sharingapp.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sharingapp.ComposeIcon


@Composable
fun AppBottomBar(
    destinations: List<AppDestination>,
    currentDestination: String = AppDestination.HOME.name,
    // a function that takes a route and returns an action
    onNavigateToDestination: (route : String) -> Unit
)  {
    NavigationBar(
        modifier = Modifier
            .windowInsetsPadding(
                WindowInsets.safeDrawing.only(
                    WindowInsetsSides.Horizontal +
                            WindowInsetsSides.Bottom
                )
            )
            .height(70.dp)
    ) {
        destinations.forEach { destination ->
            val selected = destination.name == currentDestination
            NavigationBarItem(
                selected = selected,
                // b/c the calling fn will have a function like navController.navigate(AppDestination.DEST.NAME) {...}
                onClick = { onNavigateToDestination(destination.name) },
                icon = {
                    // TODO: different colored icon depending on whether selected or not
                    val icon = destination.icon
                    Icon(
                        ComposeIcon.asImageVector(destination.icon),
                        modifier = Modifier.size(16.dp),
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = destination.name
                    )
                }
            ) // end NavigationBarItem
        } // end fun AppBottomBar
    }
}