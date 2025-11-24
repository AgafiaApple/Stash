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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sharingapp.ComposeIcon
import com.example.sharingapp.ContactsIcon
import com.example.sharingapp.model.Item
import com.example.sharingapp.ui.utils.Dimens.Spacing
import com.example.sharingapp.ui.utils.ExpandableItemCard



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemsScreen(
    isExpandedScreen: Boolean,
    viewModel: ItemsViewModel,
    innerPadding : PaddingValues
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    // track which (if any) card is expanded
    var expandedItemId  by remember { mutableStateOf<Long?>(null) }
    // the function to pass into each item's row
    val onToggle = {item : Item ->
        // if the toggled item is closed, open it (closing other cards)
        if (item.id != expandedItemId ) {
            expandedItemId = item.id
        // if the toggled item is already open, close it
        } else {
            expandedItemId = null
        }
    }
    // passing down innerPadding to ensure the app does not draw behind the the status bar and navigation
    //   bar of the device's interface, ensuring the list starts and ends within the range of visibility
    LazyColumn(contentPadding = innerPadding) {
        items(uiState.items, key = { item -> item.id }) { item ->
            // TODO: add to item row code here
            val isExpanded =
            ItemRow(item = item, onClick = { onToggle(item)}, isExpanded = (expandedItemId == item.id))
            }
        }

} // end ItemsScreen

@Composable
fun ItemRow(item : Item, onClick: () -> Unit, modifier: Modifier = Modifier, isExpanded : Boolean) {
    ExpandableItemCard(
        item = item,
        onToggle = onClick,
        isExpanded = isExpanded,
        cardTitle = item.title,
        cardSubtitle = item.maker
    )


//        Row(
//            modifier = Modifier.padding(vertical = Spacing.Medium),  // TODO: complete later
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            // TODO: include icon or photo here
//                /*
//                 * Pseudo-code for icon/photo display
//                 *      if (item has image):
//                 *          use the image uploaded by the user
//                 *      else:
//                 *          use a default icon
//                 */
//
//            Spacer(Modifier.width(Spacing.Medium))
//
//            // text content
//            Column(modifier = Modifier.weight(1f)) {
//                Text(
//                    text = item.title,
//                    style = MaterialTheme.typography.titleMedium
//                )
//
//                Text(
//                    text = item.status.name,
//                    style = MaterialTheme.typography.bodyMedium,
//                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = .6f)
//                )
//            } // end inner column
//
//        } // end Row



    } // end ItemRow fn
