package com.example.sharingapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Displays a list of all items
 */
class AllItemsFragment : ItemsFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        super.setVariables(R.layout.all_items_fragment, R.id.my_items)
        super.setAdapter(this) // Changed AllItemsFragment.this to this

        return rootView
    }

    fun filterItems(): ArrayList<Item> {
        return item_list.getItems()
    }
}
