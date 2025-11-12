package com.example.sharingapp

import android.util.Log
import kotlinx.serialization.Serializable

@Serializable
data class ItemList(val items : ArrayList<Item>)

// I think this is good practice...
// promoting functional-style, more bug-free code because
// we isolate side effects
class ItemListConstructor {
    var items = ArrayList<Item>()

    fun addItem(item : Item) : Boolean {
        return items.add(item)
    }

    fun deleteItem(item : Item) : Boolean {
        return items.remove(item)
    }

    fun getItemFromIdx(idx : Int) : Item? {
        if (idx < items.size) {
            return items[idx]
        }

        // log the event if index is out of bounds
        Log.d(null, "Index out of bounds; returning null")

        return null
    }

    fun toItemList() : ItemList {
        return ItemList(items=items)
    }

    fun filterList(items_ls : ItemList, filter : Status) : ItemList {
        var filtered_arr = ArrayList<Item>()

        // add items that match the filter param
        // to the filtered_arr
        for (item in items_ls.items) {
            if (item.status == filter) {
                filtered_arr.add(item)
            }
        } // end for


        return ItemList(filtered_arr)

    } // end filterList()

} // end ItemListConstructor

class JsonifyItemList : Jsonify<ItemList> {
    // we can use .serializer() b/c ItemList is
    // a @Serializable data class
    override val serializer = ItemList.serializer()
}