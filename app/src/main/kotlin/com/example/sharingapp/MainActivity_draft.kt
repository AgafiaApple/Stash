package com.example.sharingapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.material.Text
import androidx.compose.foundation.lazy.items
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sharingapp.ItemList
import com.example.sharingapp.ItemListConstructor
import com.example.sharingapp.JsonifyItemList
import com.example.sharingapp.model.Status
import java.io.File


class MainActivity_draft : AppCompatActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val items_filepath = filesDir.absolutePath.plus("items.json")
        val items_file = File(items_filepath)

        // make sure the file is created if it does not already exist
        if (!items_file.isFile) {
            items_file.createNewFile()
        }

        // TODO: move the variables outside of this as lateinit
        val json_str = JsonFileOps().fromJsonFile(items_filepath)
        if (json_str != null && !json_str.isEmpty()) {
            val itemlist_temp = JsonifyItemList().fromJson(json_str)
            val items_construct = ItemListConstructor()
            for (item in itemlist_temp.items) {
                items_construct.addItem(item)
            }
        }
        else {
            val items_construct = ItemListConstructor()
        }

        /*
         * ----------------- UI CONTENT --------------------
         */

        setContent {
            val filter: MutableState<Status?> = remember { mutableStateOf(null) }


        /* Just testing to make sure I know how status works */
//            Column() {
//                Button(onClick = {
//                filter.value = Status.AVAILABLE
//            }) { Text("Available") }
//                Button(onClick = {
//                    filter.value = Status.BORROWED
//                }) { Text("Borrowed") }
//
//                Text("Current mode is ${filter.value}")}



            // TODO: use Compose instead of xml
//        val viewPager : ViewPager2 = findViewById(R.layout.activity_main)

            // create the adapter that will return a fragment for each of the
            // three primary sections of the activity

        }

    }

    @Composable
// best practice to perform any preprocessing operations on the list in the ViewModel before
// passing to the @Composable function
    fun ItemList(filtered_items: ItemList, filter: Status?) {


        // the filter decides which composable takes effect
        if (filter == Status.AVAILABLE) {
            AvailableItems(filtered_items)
        } else if (filter == Status.BORROWED) {
            BorrowedItems(filtered_items)
        } else {
            AllItems(filtered_items)
        }

    }


    @Composable
    fun AvailableItems(filtered_items: ItemList) {
        LazyColumn {
            items(
                filtered_items.items,
                key = { item ->
                    filtered_items.items.indexOf(item)
                }) { item ->
                Text(item.toString())
            }

        }
    }

    @Composable
    fun BorrowedItems(filtered_items: ItemList) {
        LazyColumn {
            items(
                filtered_items.items,
                key = { item ->
                    filtered_items.items.indexOf(item)
                }) { item ->
                Text(item.toString())
            }

        }
    }

    @Composable
    fun AllItems(filtered_items: ItemList) {
        LazyColumn {
            items(
                filtered_items.items,
                key = { item ->
                    filtered_items.items.indexOf(item)
                }) { item ->
                Text(item.toString())
            }

        }
    }

    @Composable
    fun ThemeTestScreen() {
        // A Column will arrange our test components vertically.
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp), // Add some padding around the content
            horizontalAlignment = Alignment.CenterHorizontally, // Center components horizontally
            verticalArrangement = Arrangement.spacedBy(16.dp) // Add space between components
        ) {
            // Test 1: Typography and Primary Text Color
            // This Text will use the primary text color (onBackground) and different font styles from your Type.kt.
            Text(
                text = "Display Large",
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Headline Medium",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "Body Small",
                style = MaterialTheme.typography.bodySmall
            )

            // Test 2: Primary and Secondary Colors with a Button
            // The Button automatically uses the "primary" color for its container
            // and the "onPrimary" color for its content (the text).
            Button(onClick = { /* Do nothing */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Text("Primary Color", color=MaterialTheme.colorScheme.onPrimaryContainer)
            }

            // Test 3: Secondary Color with a FloatingActionButton
            // The FAB by default uses the "secondary" container color.
//            FloatingActionButton(onClick = { /* Do nothing */ }) {
//                Icon(Icons.Default.Add, contentDescription = "Add")
//            }

            // Test 4: Tertiary and Surface Colors with a Card
            // Cards use the "surface" color. We can put a colored element inside.
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer,
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(MaterialTheme.colorScheme.tertiaryContainer) // Explicitly use the tertiary color
                ) {
                    Text(
                        text = "Tertiary Color",
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.onTertiaryContainer // Text color for on top of tertiary
                    )
                }
            }

            // Test 5: Error Color
            Text(
                text = "This is an error message.",
                color = MaterialTheme.colorScheme.error
            )
        }
    }

}
