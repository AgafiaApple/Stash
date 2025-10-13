package com.example.sharingapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup



/**
 * Displays a list of all "Available" items
 */


class AvailableItemsFragment : ItemsFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_borrowed_items, container, false) // Initialize rootView
        super.setVariables(R.layout.fragment_available_items, R.id.my_available_items)
        super.setAdapter(this) // Changed AvailableItemsFragment.this to this

        return rootView
    }

    override fun filterItems(): ArrayList<Item>? {
        val status = "Available"
        return item_list.filterItemsByStatus(status)
    }
}
