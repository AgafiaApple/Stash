package com.example.sharingapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items // must use if passing a list to `items()` in LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sharingapp.model.Contact
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sharingapp.AddBoxIcon
import com.example.sharingapp.ComposeIcon
import com.example.sharingapp.MenuVertIcon
import com.example.sharingapp.R
import com.example.sharingapp.ui.utils.CircleInitialIcon
import com.example.sharingapp.ui.utils.ContactOption
import com.example.sharingapp.ui.utils.Dimens
import com.example.sharingapp.ui.utils.ExpandableCard
import com.example.sharingapp.ui.utils.MenuButton
import com.example.sharingapp.ui.utils.OptionsEnum
import com.example.sharingapp.ui.utils.NotImplemented



@Composable
fun ContactsScreen(
    isExpandedScreen : Boolean,
    viewModel : ContactsViewModel,
    inner_padding : PaddingValues = PaddingValues()

) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var expandedContactId by remember {mutableStateOf<Long?>(null)}

    var showAddBoxDialog by remember {mutableStateOf(false)}

    val onToggleContact = {contact : Contact ->
        // if the contact is clicked when closed, expand it
        if (contact.id != expandedContactId) {
            expandedContactId = contact.id
        }
        // if the toggled contact is already open, close it
        else {
            expandedContactId = null
        }

    }

    // we need this outer box to keep the list and add-icon from overlapping
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(contentPadding = inner_padding) {
            items(uiState.contacts, key = {contact -> contact.id}) { contact ->
                // by default, extra info about contact not shown
                var showClickContact by remember {mutableStateOf(false)}

                // each contact has the option to delete it
                val onDeleteContact = {
                    viewModel.deleteContact(contact.id)

                }

                // use a row to separate the profile icon from the row
                Row(
                    modifier = Modifier.padding(6.dp, 0.dp, 0.dp, 0.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // profile icon
                    CircleInitialIcon(
                        name = contact.username,
                        surfaceColor = MaterialTheme.colorScheme.inversePrimary,
                        textColor = MaterialTheme.colorScheme.primary
                    )

                   // main UI - the row
                    ContactRowExpandable(
                        contact = contact,
                        onDeleteContact = onDeleteContact,
                        onClickContact = { onToggleContact(contact) },
                        isExpanded = (expandedContactId == contact.id)
                    )

                }


                // inform the user that the "more information" for contact has not been implemented yet
                if(showClickContact) {
                    ClickContactNotImplemented(onDismiss = {showClickContact = false})
                }
            } // end items block
        } // end LazyColumn block

        // add-item button at the bottom right of the screen
            // TODO: onClick should navigate the user to the AddContact page
            FloatingActionButton(
                onClick = {showAddBoxDialog = true},
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(Dimens.Spacing.Medium),
                elevation = FloatingActionButtonDefaults.elevation(2.dp),
                containerColor = MaterialTheme.colorScheme.inversePrimary
            ) {
                Icon(
                    imageVector = ComposeIcon.asImageVector(AddBoxIcon()),
                    contentDescription = "Add contact"
                )

            }
    } // end outer box block

    if (showAddBoxDialog) {
        NotImplemented(
            onDismiss = {showAddBoxDialog = false},
            "Function not implemented",
            "The add contact feature has not been implemented yet"
            )
    }

} // end ContactsScreen

@Composable
fun ContactRowExpandable(
    contact : Contact,
    onClickContact : () -> Unit,
    // keep the viewModel interaction at the topmost level of the UI
    onDeleteContact : () -> Unit,
    isExpanded : Boolean
) {
    // only show the option if "show" is true
    var showEditDialog by remember {mutableStateOf(false)}

    var showDeleteDialog by remember {mutableStateOf(false)}

    // param for the menu composable
    val options = listOf(
        OptionsEnum.EDIT,
        OptionsEnum.DELETE
    )

    // param for the menu composable
    // the order of the onClick list must correspond with the options list
    val onClickOptions = listOf(
        // dialog for editing a contact is shown
        {showEditDialog = true},
        // dialog for deleting a contact is shown
        {showDeleteDialog = true}
    )


    ExpandableCard(
        contact,
        isExpanded = isExpanded,
        onToggle = onClickContact,
        cardTitle = contact.displayName,
        cardSubtitle = "@${contact.username}",
        cardDescription = contact.description,
        menuOptions = options,
        menuOnClickOptions = onClickOptions,
        imagePainter = painterResource(R.drawable.image_placeholder)
    )

    /* 3. If the user clicked one of the menu options */
    if (showEditDialog) {
        val onDismissRequest = {showEditDialog = false}
        // TODO: replace with a dialog that uses an onEditContact function passed into the row
        NotImplemented(onDismiss = onDismissRequest, "Function not implemented", "The edit contact feature has not been implemented yet")
    }

    if (showDeleteDialog) {
        val onDismissRequest = {showDeleteDialog = false}
        DeleteContact(
            onDismissRequest = onDismissRequest,
            onConfirmation = {
                onDeleteContact()
                showDeleteDialog = false // close dialog box
            },
            contact = contact
        )

    } // end if-statement


} // end ContactRowExpandable composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteContact(contact : Contact, onDismissRequest : () -> Unit, onConfirmation : () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(text = "Delete ${contact.username}?") },
        text = { Text("This action cannot be undone") },

        // The user deletes the contact
        confirmButton = {
            Button(
                onClick = onConfirmation
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
} // end DeleteContact composable



@Composable
fun ClickContactNotImplemented(onDismiss : () -> Unit) {
    // Alert dialog must have at least an onDismiss and at least one Button
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Function not implemented")},
        text = { Text("The \"click contact\" feature has not been implemented yet") },
        // the user confirms that they understand and the dialog closes
        confirmButton = {
            TextButton(
                onClick = onDismiss
            ) {Text("Okay")}
        }


    )
}




