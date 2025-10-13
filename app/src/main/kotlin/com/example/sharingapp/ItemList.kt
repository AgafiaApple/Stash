// TODO: I need to make this look like my ContactList code instead of the automatic Java => Kotlin translation

package com.example.sharingapp

import android.content.Context
//import com.google.gson.Gson
//import com.google.gson.reflect.TypeToken
import java.io.*
import java.lang.reflect.Type



/**
 * ItemList class
 */
class ItemList {

    private var items: ArrayList<Item> = ArrayList()
    private val FILENAME = "items.dat" // Changed to .dat for object serialization

    fun setItems(item_list: ArrayList<Item>) {
        items = item_list
    }

    fun getItems(): ArrayList<Item> {
        return items
    }

    fun addItem(item: Item) {
        items.add(item)
    }

    fun deleteItem(item: Item) {
        items.remove(item)
    }

    fun getItem(index: Int): Item {
        return items[index]
    }

    fun getIndex(item: Item): Int {
        for ((index, i) in items.withIndex()) { // Use withIndex for cleaner iteration
            if (item.id == i.id) { // Access id directly
                return index
            }
        }
        return -1
    }

    fun getSize(): Int {
        return items.size
    }

    // I need to make this look like my ContactList code instead of the automatic Java => Kotlin translation


    fun loadItems(context: Context): ArrayList<Item> {
        val itemsFile = getItemsFile(context)

        // 1. Open the file for reading
        //      ObjectInputStream reads the object from the file
        //      `use` ensures the file is closed immediately after use
        var items: ArrayList<Item>? = ObjectInputStream(FileInputStream(itemsFile)).use {
            // 2. Read the object from the file
            //      objectInputStream.readObject() reads the object that was written to the file
            it.readObject() as? ArrayList<Item>
        }

        if (items == null) {
            println("Warning! `items` was loaded as `null`. This means either the user has no" +
                    " items yet or that an error occurred.")
        }

        return items ?: ArrayList()
    } // end loadItems()

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

    // saves the items in a file in ArrayList<Item> format
    fun saveItems(context: Context, items: ArrayList<Item>) {
        val itemsFile = getItemsFile(context)

        ObjectOutputStream(FileOutputStream(itemsFile)).use {
            it.writeObject(items)
        }
    } // end saveItems()

    fun filterItemsByStatus(status: String): ArrayList<Item> {
        val selectedItems = ArrayList<Item>()
        for (i in items) { // Simplified iteration
            if (i.status == status) { // Access status directly
                selectedItems.add(i)
            }
        }
        return selectedItems
    }

    fun getActiveBorrowers(): ArrayList<Contact> {
        val activeBorrowers = ArrayList<Contact>()
        for (i in items) {
            val borrower = i.borrower
            if (borrower != null) {
                activeBorrowers.add(borrower)
            }
        }
        return activeBorrowers
    }
}
