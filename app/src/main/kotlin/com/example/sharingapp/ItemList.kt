// TODO: I need to have more safety checks in place for the possibility of `items` being null

package com.example.sharingapp

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.*



/**
 * ItemList class
 */
class ItemList {

    private var items : ArrayList<Item>? = null
    private val FILENAME = "items.json"

    fun setItems(item_list: ArrayList<Item>) {
        items = item_list
    }

    fun getItems(): ArrayList<Item>? {
        return items
    }

    fun addItem(item: Item) {
        items!!.add(item)
    }

    fun deleteItem(item: Item) {
        items!!.remove(item)
    }

    fun getItem(index: Int): Item {
        return items!![index]
    }

    fun getIndex(item: Item): Int {
        for ((index, i) in items!!.withIndex()) { // Use withIndex for cleaner iteration
            if (item.id == i.id) { // Access id directly
                return index
            }
        }
        return -1
    }

    fun getSize(): Int {
        return items!!.size
    }

    fun loadItems(context: Context) {
        val gson = Gson()
        val reader = getItemsFile(context).bufferedReader()
        val listType = object : TypeToken<ArrayList<Item>>() {}.type
        this.items = gson.fromJson(reader, listType)
    }


    private fun getItemsFile(context: Context): File {
        val itemsFile = File(context.filesDir, FILENAME)

        if (!itemsFile.exists()) {
            try {
                if (itemsFile.createNewFile()) {
                    println("File created: ${itemsFile.absolutePath}")
                } else {
                    println("File already exists - check for errors")
                }
            } catch (e: IOException) {
                e.printStackTrace()
                println("Error creating file ${e.message}")
            }
        } else {
            println("File already exists: ${itemsFile.absolutePath}")
        }

        return itemsFile
    } // end getItemsFile

    fun saveItems(context: Context) {
        val gson = Gson()
        val writer = getItemsFile(context).bufferedWriter()
        gson.toJson(this.items, writer)
    }


    fun filterItemsByStatus(status: String): ArrayList<Item> {
        val selectedItems = ArrayList<Item>()
        for (i in this.items!!) { // Simplified iteration
            if (i.status == status) { // Access status directly
                selectedItems.add(i)
            }
        }
        return selectedItems
    }

    fun getActiveBorrowers(): ArrayList<Contact> {
        val activeBorrowers = ArrayList<Contact>()
        for (i in this.items!!) {
            val borrower = i.borrower
            if (borrower != null) {
                activeBorrowers.add(borrower)
            }
        }
        return activeBorrowers
    } // end getActiveBorrowers

} // end ItemList class
