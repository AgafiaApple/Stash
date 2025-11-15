package com.example.sharingapp

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.runtime.Composable
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import androidx.viewpager2.adapter.FragmentStateAdapter
import 	androidx.viewpager2.widget.ViewPager2
import androidx.compose.material.Text
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import java.io.File


class MainActivity : AppCompatActivity() {
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
}
