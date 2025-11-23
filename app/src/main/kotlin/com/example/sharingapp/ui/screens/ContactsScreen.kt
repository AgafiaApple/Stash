package com.example.sharingapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items // must use if passing a list to `items()` in LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.sharingapp.ComposeIcon
import com.example.sharingapp.MenuVertIcon
import com.example.sharingapp.ui.utils.CircleInitialIcon
import com.example.sharingapp.ui.utils.ContactOption
import com.example.sharingapp.ui.utils.Dimens
import com.example.sharingapp.ui.utils.MenuButton
import com.example.sharingapp.ui.utils.OptionsEnum

@Composable
fun ContactsScreen(
    isExpandedScreen : Boolean,
    viewModel : ContactsViewModel,
    onNavigateToItems : () -> Unit,
    inner_padding : PaddingValues = PaddingValues()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LazyColumn(contentPadding = inner_padding) {
        items(uiState.contacts, key = {contact -> contact.id}) { contact ->
            ContactRow(contact, onClickOptions  = {

            },
                onClickContact = {

                }
            )
        }
    }
}

@Composable
fun ContactRow(contact : Contact, onClickContact : () -> Unit) {
    val firstInitial = contact.username.take(0)
    Card(
        modifier = Modifier.padding(Dimens.Spacing.Medium)
    ) {
        Button(onClick = onClickContact) {
            ListItem(
                headlineContent = { Text(contact.username) },
                // TODO: Contact class should include a brief description -- and the card can slide down to show the whole description if the user clicks on it
//            supportingContent = { Text(contact.email) },
                colors = ListItemDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                leadingContent = {
                    CircleInitialIcon(
                        contact.username,
                        surfaceColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(.5f),
                        textColor = MaterialTheme.colorScheme.primary
                    )
                },
                trailingContent = {
                    // prerequisites for the menu composable
                    val options = listOf(
                        ContactOption(OptionsEnum.ADD),
                        ContactOption(OptionsEnum.EDIT),
                        ContactOption(OptionsEnum.DELETE)
                    )
                    // the order of the onClick list must correspond with the actions of
                    // the options list
                    val onClickOptions = listOf(

                    )
                    // our custom menu composable
                    MenuButton()
                }
            ) // end ListItem
        } // end Button
    } // end Row block
}




