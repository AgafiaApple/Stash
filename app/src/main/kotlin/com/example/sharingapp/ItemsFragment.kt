
package com.example.sharingapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment

/**
 * Superclass of AvailableItemsFragment, BorrowedItemsFragment and AllItemsFragment
 */
abstract class ItemsFragment : Fragment() {

    val item_list = ItemList()
    var rootView: View? = null
    private var list_view: ListView? = null
    private var adapter: ArrayAdapter<Item>? = null
    private var selected_items: ArrayList<Item>? = null
    private var inflater: LayoutInflater? = null
    private var container: ViewGroup? = null
    private var context: Context? = null

    // TODO: What does this do??? Why is context assigned to itself?
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        context = context // or getContext()
        item_list.initializeItemList(requireContext())
        this.inflater = inflater
        this.container = container

        return rootView
    }

    fun setVariables(resource: Int, id: Int) {
        rootView = inflater?.inflate(resource, container, false)
        list_view = rootView?.findViewById(id)
        selected_items = filterItems()
    }

    fun setAdapter(fragment: Fragment) {
        context?.let { context -> // Null-safe context
            selected_items?.let { selectedItems -> // Null-safe selected_items
                adapter = ItemAdapter(context, selectedItems, fragment)
                list_view?.adapter = adapter
                adapter?.notifyDataSetChanged()

                // When item is long clicked, this starts EditItemActivity
                list_view?.setOnItemLongClickListener { parent, view, pos, id ->
                    val item = adapter?.getItem(pos)

                    item?.let { item -> // Null-safe item
                        val meta_pos = item_list.getIndex(item)
                        if (meta_pos >= 0) {
                            val edit = Intent(context, EditItemActivity::class.java)
                            edit.putExtra("position", meta_pos)
                            startActivity(edit)
                        }
                    }
                    true
                }
            }
        }
    }

    /**
     * filterItems is implemented independently by AvailableItemsFragment, BorrowedItemsFragment and AllItemsFragment
     * @return selected_items
     */
    abstract fun filterItems(): ArrayList<Item>?
}
