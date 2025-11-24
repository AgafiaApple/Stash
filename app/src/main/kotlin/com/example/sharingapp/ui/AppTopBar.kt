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
fun ScreenTopBar(
    screenTitle : String
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = screenTitle)
        }
    ) // end CenterAlignedTopAppBar

} // end AppTopBar fun