package com.example.sharingapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sharingapp.ComposeIcon
import com.example.sharingapp.ContactsIcon
import com.example.sharingapp.model.Item

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemsScreen(
    isExpandedScreen: Boolean,
    viewModel: ItemsViewModel,
    innerPadding : PaddingValues
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        LazyColumn(contentPadding = innerPadding) {
            items(uiState.items, key = { item -> item.id }) { item ->
                // TODO: add to item row code here
                ItemRow(item = item, onClick = { /* TODO: brainstorm what design component to use for options and fill in later */ })
            }
        }

} // end ItemsScreen

@Composable
fun ItemRow(item : Item, onClick: () -> Unit, modifier: Modifier = Modifier ) { /* TODO: add onClick later */

    // 1.
        Row(
            modifier = Modifier.padding(vertical = 16.dp),  // TODO: complete later
            verticalAlignment = Alignment.CenterVertically
        ) {
            // TODO: include icon or photo here

            Spacer(Modifier.width(16.dp))

            // text content
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = item.status.name,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = .6f)
                )
            } // end inner column
        } // end Row



    } // end ItemRow fn