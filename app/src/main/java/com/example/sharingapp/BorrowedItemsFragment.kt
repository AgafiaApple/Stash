package com.example.sharingapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Displays a list of all "Borrowed" items
 */
class BorrowedItemsFragment : ItemsFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.borrowed_items_fragment, container, false) // Initialize rootView
        super.setVariables(R.layout.borrowed_items_fragment, R.id.my_borrowed_items)
        super.setAdapter(this) // Changed BorrowedItemsFragment.this to this

        return rootView // Return rootView
    }

    fun filterItems(): ArrayList<Item> {
        val status = "Borrowed"
        return item_list.filterItemsByStatus(status)
    }
}
