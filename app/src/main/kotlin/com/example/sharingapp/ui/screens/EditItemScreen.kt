package com.example.sharingapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sharingapp.model.Item
import com.example.sharingapp.ui.ScreenTopBar

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun EditItemScreen(
    isExpandedScreen: Boolean,
    viewModel: ItemsViewModel,
    innerPadding : PaddingValues,
    item : Item
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val defaultText = "Empty"

    Column {
        //  Appbar with the screen title
        ScreenTopBar(item.title)
        // TODO: Stats - how many times it has been borrowed, it's current status

        // Item title field
        narrowTextBox(text = defaultText,  onValueChange = {}, )

        // Item maker field
        narrowTextBox(text = defaultText, onValueChange = {})

        // Item description
        largeTextBox(text = defaultText, onValueChange = {})

        // Item dimensions
        narrowTextBox(text =  defaultText, onValueChange = {})

        // TODO: make private toggle
    }




}

@Composable
fun largeTextBox(text : String? = null,
                 onValueChange : (String) -> Unit,
                 modifier : Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxSize()
    ) {
        TextField(
            value = text ?: "",
            onValueChange = { onValueChange } // accept the new string and pass it back up
        )
    }
}

@Composable
fun narrowTextBox(
    text : String?,
    onValueChange: (String) -> Unit,
    modifier : Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        TextField(
            value = text ?: "",
            onValueChange = {onValueChange}
        )
    }
}