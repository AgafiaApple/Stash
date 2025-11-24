package com.example.sharingapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.sharingapp.AddBoxIcon
import com.example.sharingapp.ComposeIcon
import com.example.sharingapp.ContactsIcon
import com.example.sharingapp.model.Contact
import com.example.sharingapp.model.Item
import com.example.sharingapp.ui.AppDestination
import com.example.sharingapp.ui.utils.Dimens
import com.example.sharingapp.ui.utils.Dimens.Spacing
import com.example.sharingapp.ui.utils.ExpandableCard
import com.example.sharingapp.ui.utils.OptionsEnum
import kotlinx.coroutines.CoroutineScope


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemsScreen(
    isExpandedScreen: Boolean,
    viewModel: ItemsViewModel,
    innerPadding : PaddingValues,
    navController : NavHostController,
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

    // wrapping content in a box so we can add a button to the bottom right
    Box(
        Modifier.fillMaxSize()
    ){
        // passing down innerPadding to ensure the app does not draw behind the the status bar and navigation
        LazyColumn(contentPadding = innerPadding) {
            items(uiState.items, key = { item -> item.id }) { item ->

                // only show delete dialog for an item if showDeleteDialog is true
                // TODO: not likely, but I should still make sure that user's are not able to open multiple delete dialogs at once
                var showDeleteDialog by remember { mutableStateOf(false) }


                // OPTIONS FOR THE MENU
                val options = listOf<OptionsEnum>(
                    OptionsEnum.EDIT,
                    OptionsEnum.DELETE
                )
                val onClickOptions = listOf<() -> Unit>(
                    // First function in the list: go to edit screen
                    {
                        val route = "${AppDestination.EDIT_ITEM.name}/${item.id}"
                        navController.navigate(route){
                            popUpTo(navController.graph.findStartDestination().id)
                            }

                    },

                    // Second function in the list
                    {
                        showDeleteDialog = true
                    }
                )

                //
                val onConfirmDelete = {
                    showDeleteDialog = false


                }
                ItemRow(
                    item = item,
                    onClick = { onToggle(item) },
                    isExpanded = (expandedItemId == item.id),
                    menuOptions = options,
                    menuOptionsOnClick = onClickOptions,
                )

                // show the delete dialog ONLY if showDeleteDialog == true
                if (showDeleteDialog) {
                    DeleteItem(
                        onDismissRequest = {showDeleteDialog = false},
                        onConfirmation = { onConfirmDelete() },
                        item = item
                    )
                } // end if
            } // end items block
        } // end LazyColumn block



        FloatingActionButton(
            onClick = {
                navController.navigate(AppDestination.ADD_ITEM.name)
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(Dimens.Spacing.Medium),
            elevation = FloatingActionButtonDefaults.elevation(2.dp),
            containerColor = MaterialTheme.colorScheme.inversePrimary

        ) {
            Icon(
                imageVector = ComposeIcon.asImageVector(AddBoxIcon()),
                contentDescription = "Add item"
            )

        }


    } // end Box wrapper

} // end ItemsScreen

@Composable
fun ItemRow(item : Item,
            onClick: () -> Unit,
            modifier: Modifier =
                Modifier,
            isExpanded : Boolean,
            imagePainter : Painter? = null,
            menuOptions : List<OptionsEnum>,
            menuOptionsOnClick : List<() -> Unit>
            ) {

    ExpandableCard(
        item = item,
        onToggle = onClick,
        isExpanded = isExpanded,
        cardTitle = item.title,
        cardSubtitle = item.maker,
        menuOptions = menuOptions,
        menuOnClickOptions = menuOptionsOnClick,
    )



} // end ItemRow fun

@Composable
fun DeleteItem(item : Item, onDismissRequest : () -> Unit, onConfirmation : (Item) -> Unit) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(text = "Delete ${item.title}?") },
        text = { Text("This action cannot be undone") },

        // The user deletes the contact
        confirmButton = {
            Button(
                onClick = { onConfirmation(item) }
            ) {
                Text("Delete")
            }
        },
        // the user does NOT delete the contact
        dismissButton = {
            // Add a cancel button so the user isn't stuck
            TextButton(onClick = onDismissRequest) {
                Text("Cancel")
            }
        }
    )
}
