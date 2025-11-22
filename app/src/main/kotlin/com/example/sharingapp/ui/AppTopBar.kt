package com.example.sharingapp.ui

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.sharingapp.ComposeIcon
import com.example.sharingapp.ContactsIcon
import com.example.sharingapp.ProfileIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    onNavigateToProfile : () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = "My Stash")
        },
        actions = {
            // navigation button
            // TODO: replace with onNavigateToProfile and Profile icon
            IconButton(onClick = onNavigateToProfile) {
                Icon(
                    ComposeIcon.asImageVector(ProfileIcon()),
                    contentDescription = "Profile"
                )
            }
        } // end actions parameter
    ) // end CenterAlignedTopAppBar

} // end AppTopBar fun