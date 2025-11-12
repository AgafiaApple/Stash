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



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: uncomment this when you figure out why findViewById is giving null
//        val viewPager : ViewPager2 = findViewById(R.layout.activity_main)

        // create the adapter that will return a fragment for each of the
        // three primary sections of the activity

    }

}

@Composable
// best practice to perform any preprocessing operations on the list in the ViewModel before
// passing to the @Composable function
fun ItemList(filtered_items : ItemList, filter : Status? = null ) {

    // the filter decides what fragment we use
    if (filter == Status.AVAILABLE) {
        val fragment = AvailableItemsFragment(filtered_items)
    }
    else if (filter == Status.BORROWED) {
        val fragment = BorrowedItemsFragment(filtered_items)
    }
    else {
        val fragment = AllItemsFragment(filtered_items)
    }

    LazyColumn {
        items(filtered_items.items,
            key = { item ->
                filtered_items.items.indexOf(item)
            }) {
            item ->
            Text(item.toString())
        }

    }
}
