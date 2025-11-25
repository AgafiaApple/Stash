package com.example.sharingapp.ui.utils

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun NotImplemented(onDismiss : () -> Unit, title : String, text : String) {
    // Alert dialog must have at least an onDismiss and at least one Button
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title)},
        text = { Text(text) },
        // the user confirms that they understand and the dialog closes
        confirmButton = {
            TextButton(
                onClick = onDismiss
            ) {Text("Okay")}
        }


    )
}