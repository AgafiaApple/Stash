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
    private val FILENAME = "items.sav"

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
        return items[index] // Simplified access using []
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
    fun loadItems(context: Context) {
        try {
            context.openFileInput(FILENAME).use { fis -> // Use 'use' for automatic resource management
                InputStreamReader(fis).use { isr ->
                    val gson = Gson()
                    val listType: Type = object : TypeToken<ArrayList<Item>>() {}.type
                    items = gson.fromJson(isr, listType) ?: ArrayList() // Provide default value
                }
            }
        } catch (e: FileNotFoundException) {
            items = ArrayList()
        } catch (e: IOException) {
            items = ArrayList()
        }
    }

    // I need to make this look like my ContactList code instead of the automatic Java => Kotlin translation
    fun saveItems(context: Context) {
        try {
            context.openFileOutput(FILENAME, 0).use { fos -> // Use 'use' for automatic resource management
                OutputStreamWriter(fos).use { osw ->
                    val gson = Gson()
                    gson.toJson(items, osw)
                    osw.flush()
                }
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

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
