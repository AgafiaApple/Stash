package com.example.sharingapp.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sharingapp.ui.screens.ContactRowExpandable
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewModelScope
import com.example.sharingapp.R
import com.example.sharingapp.model.Contact
import com.example.sharingapp.ui.utils.ExpandableCard
import com.example.sharingapp.ui.utils.OptionsEnum
import androidx.compose.material3.Text
import kotlinx.coroutines.launch

@Composable
fun OtherUsersScreen(
    viewModel : OtherUsersViewModel,
    innerPadding : PaddingValues = PaddingValues(),
    isExpandedScreen : Boolean
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var expandedContactId by remember {mutableStateOf<Long?>(null)}


    // TODO: turn this into a function that can be called for any row (reduces repeated coding)
    val onToggleContact = {user : Contact ->
        // if the user is closed and then clicked, it expands
        if (expandedContactId != user.id) {
            expandedContactId = user.id
        }
        // if the user is open and clicked, it closes
        else {
            expandedContactId = null
        }
    }

    LazyColumn(contentPadding = innerPadding) {
        items(uiState.contacts, key = {user -> user.id}) { user ->

            ExpandableCard(
                item = user,
                isExpanded = (expandedContactId == user.id),
                onToggle = {onToggleContact(user)},
                cardTitle = user.displayName,
                cardSubtitle = "@${user.username}",
                cardDescription = user.description,
                imagePainter = painterResource(R.drawable.image_placeholder),
                // TODO: implement menu options and corresponding onClick options
                menuOptions = listOf(
                    OptionsEnum.ADD
                ),
                menuOnClickOptions = listOf(
                    {}
                )

            )

            // TODO: Implement snackbar
//            ContactAddedSnackbar(user, viewModel = viewModel)

        }
    }


}

//@Composable
//fun ContactAddedSnackbar(contact : Contact, viewModel: OtherUsersViewModel) {
//    val scope = viewModel.viewModelScope
//    val snackbarHostState = remember { SnackbarHostState() }
//
//    Scaffold(
//        snackbarHost = {
//            SnackbarHost(hostState = snackbarHostState)
//        },
//        floatingActionButton = {
//            ExtendedFloatingActionButton(
//                text = {Text("${contact.displayName} added to contacts")},
//                onClick = {
//                    scope.launch {
//                        snackbarHostState.showSnackbar("")
//                    }
//                }
//            )
//        }
//    ) {
//    }
//}

