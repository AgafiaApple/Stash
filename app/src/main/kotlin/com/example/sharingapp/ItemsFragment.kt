/*
 * What do we need this fragment to do?
 *  This fragment is an abstract fragment that can be extended by AllItemsFragment, BorrowedItemsFragment, and AvailableItemsFragment
 *  It implements a ListView to display the items
 */

package com.example.sharingapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment

abstract class ItemsFragment :  Fragment() {

    val item_list = ItemList()
    var rootView: View? = null
    private var list_view: ListView? = null
    private var adapter: ArrayAdapter<Item>? = null
    private var selected_items: ArrayList<Item>? = null
    private var inflater: LayoutInflater? = null
    private var container: ViewGroup? = null
    private var context: Context? = null

    // what occurs in order to replace the default view
    override fun onCreateView(

        inflator: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        context = context
        // initialize item list
//        item_list.inititializeList

        this.inflater = inflator
        this.container = container

        return rootView

    }

    // customize the bodies of the below methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }

}