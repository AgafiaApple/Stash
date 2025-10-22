// TODO: I need to have more safety checks in place for the possibility of `items` being null

package com.example.sharingapp

import android.content.Context
import com.google.gson.Gson
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

    // I need to make this look like my ContactList code instead of the automatic Java => Kotlin translation


//    fun loadItems(context: Context) {
//        val itemsFile = getItemsFile(context)
//
//        // 1. Open the file for reading
//        //      ObjectInputStream reads the object from the file
//        //      `use` ensures the file is closed immediately after use
//        // TODO: Error originates from here! FIX!!!!!!!!
//        var items: ArrayList<Item>? = ObjectInputStream(FileInputStream(itemsFile)).use {
//            // 2. Read the object from the file
//            //      objectInputStream.readObject() reads the object that was written to the file
//            it.readObject() as? ArrayList<Item>
//        }
//
//        if (items == null) {
//            println("Warning! `items` was loaded as `null`. This means either the user has no" +
//                    " items yet or that an error occurred.")
//        }
//
//        this.items = items
//    } // end loadItems()

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
//    fun saveItems(context: Context) {
//        val itemsFile = getItemsFile(context)
//
//        ObjectOutputStream(FileOutputStream(itemsFile)).use {
//            it.writeObject(this.items)
//        }
//    } // end saveItems()

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
    }
}

/*
 * We have to create a data class for Item so that Item can be converted to and from Json format
 */
data class ItemData(val title : String, val maker : String, val description : String, val dimensions : Dimensions)
