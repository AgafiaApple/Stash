package com.example.sharingapp.ui.screens
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.sharingapp.model.Contact
import com.example.sharingapp.model.Dimensions
import com.example.sharingapp.model.Item
import com.example.sharingapp.ui.AppDestination
import com.example.sharingapp.ui.ScreenTopBar
import com.example.sharingapp.ui.utils.Dimens.Card
import com.example.sharingapp.ui.utils.Dimens.Spacing
import kotlinx.coroutines.launch
import com.example.sharingapp.ui.utils.NarrowTextBox
import com.example.sharingapp.ui.utils.LargeTextBox
import kotlinx.coroutines.CoroutineScope

@Composable
fun AddItemScreen(
    viewModel: ItemsViewModel,
    innerPadding: PaddingValues,
    isExpandedScreen : Boolean = false,
    navController : NavHostController,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // only becomes true if the user decides to cancel their changes
    var showCancelDialog by remember { mutableStateOf(false) }



    // using remember(item) instead of just remember so that remember only every has the newest UI state
    // Still not sure exactly why it works better...
    var title by remember { mutableStateOf("") }
    var maker by remember { mutableStateOf("") }
    var description by remember { mutableStateOf( "") }
    // TODO: either delete this functionality or implement it correctly
//    var dimensions by remember { mutableStateOf("") }

    // specifying the onClick functions
    val onClickSave = {
            val itemTitle = title
            val itemMaker = maker
            val itemDescription = description
            val newItem = Item(itemTitle, itemMaker, itemDescription, Dimensions(0, 0, 0))

            viewModel.onAddItem(newItem)
            navController.popBackStack()
        }


    // functions to pass to the dialog pop-up
    val onDismissRequest = { showCancelDialog = false }

    val onConfirmation = {
        // return to the items screen without saving
        showCancelDialog = false // close pop-up

        // return to previous page
        navController.popBackStack()
    } // end onConfirmation

    val onClickCancel = {

        // if any of the fields have changed, prompt the user to confirm that they want to forget their changes
        if (
            !title.isEmpty()||
            !maker.isEmpty()||
            !description.isEmpty()
        ) {
            showCancelDialog = true
        }
        // if no changes were made, no need to warn user - just navigate to the home screen
        else {
            navController.popBackStack()

        }
    } // end onClickCancel function definition

    if (showCancelDialog) {
        CancelAddDialog(
            onDismissRequest = onDismissRequest,
            onConfirmation = { onConfirmation() }
        )
    }


    // Main UI Elements
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScreenTopBar("Add Item")

        Spacer(Modifier.height(Spacing.Large))

        NarrowTextBox(
            label = "Title",
            text = title,
            onValueChange = { title = it }
        )

        Spacer(Modifier.height(Spacing.Medium))

        NarrowTextBox(
            label = "Maker",
            text = maker,
            onValueChange = { maker = it }
        )

        Spacer(Modifier.height(Spacing.Medium))

        LargeTextBox(
            label = "Description",
            text = description,
            onValueChange = { description = it }
        )

        Spacer(Modifier.height(Spacing.Medium))

        // TODO: Implement later
//        NarrowTextBox(
//            label = "Dimensions",
//            text = dimensions,
//            onValueChange = { dimensions = it }
//        )

        // SAVE button - and return to the home items screen
        TextButton(
            onClick = { onClickSave() },
            shape = RoundedCornerShape(Card.roundedCorner),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.inversePrimary)
        ) {
            Text(
                "Save",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        }

        // CANCEL button - do NOT save state of the text boxes, return to home items screen
        TextButton(
            onClick = { onClickCancel() },

            ) {
            Text(
                "Cancel",
                style = MaterialTheme.typography.bodySmall
            )
        }

    }// end AddItemScreen
}

@Composable
fun CancelAddDialog(onDismissRequest : () -> Unit, onConfirmation : () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(text = "Discard changes?") },
        text = { Text("This action cannot be undone") },

        // The user deletes the contact
        confirmButton = {
            Button(
                onClick = onConfirmation
            ) {
                Text("Yes, discard my changes")
            }
        },
        // the user does NOT delete the contact
        dismissButton = {
            // Add a cancel button so the user isn't stuck
            TextButton(onClick = onDismissRequest) {
                Text("No, continue editing")
            }
        }
    )
}