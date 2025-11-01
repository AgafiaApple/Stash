package com.example.sharingapp

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import java.io.*

/**
 * ItemList class
 */
class ItemList {
    private var items = ArrayList<Item>()
    private val FILENAME = "items.json"

    private lateinit var itemsFile : File
    val temp_first_item = Item("0", "0", "0", Dimensions("0", "0", "0"))


    fun initializeItemList(context: Context) {
        // the first line is necessary for loading the items from the JSON file because
            // .fromJason() does not allow you to write an empty or null ArrayList into it
        this.items.add(this.temp_first_item)

        this.initializeItemsFile(context)
        // loads the previously saved list of items
        this.loadItems()
    }

    // to ensure that the file is initialized as a Json file storing an ArrayList
    fun initializeItemsFile(context: Context) {
        // checking specifically if it has not been initialized since
            // we cannot use this.items == null if it has not been initialized
        if (!this::itemsFile.isInitialized) {

            this.itemsFile = File(context.filesDir, FILENAME)

            // ensure the file path exists
            if (!this.itemsFile.exists()) {
                this.itemsFile.createNewFile()
            } else {
                println("this.itemsFile already exists.")
            }

            // Check if the file is empty
            val gson = Gson()
            val reader = this.itemsFile.bufferedReader()
            val listType = object : TypeToken<ArrayList<Item>>() {} // removed the .type from the end

            // items_temp will be null if the file is empty or contains invalid data
            val items_temp : ArrayList<Item>? = gson.fromJson(reader, listType)

            // if the file is empty, then initialize it with this.items
            if (items_temp == null) {
                println("Saving the initial ArrayList<Item> in the file...")
                val writer = this.itemsFile.bufferedWriter()
                gson.toJson(this.items, writer) // throws an exception if a problem writing occurs
                println("itemsFile has been successfully initialized with this.items, an ArrayList")
            } else {
                println("itemsFile already contains ArrayList data.")
            }

        } else if (!this.itemsFile.exists()) {
            throw FileNotFoundException("this.itemsFile has been initialized but this.itemsFile's file path does not exist.")

        } else {
            println("this.itemsFile has already been initialized, and the file path is valid.")
        }
    }

    // must be called after initializeItemsFile method
    fun loadItems() {
        // data is stored in JSON format, so we need to load it back into ArrayList<Item> format
        val gson = Gson()
        val reader = this.itemsFile.bufferedReader()
        val listType = object : TypeToken<ArrayList<Item>>() {}
        this.items = gson.fromJson(reader, listType)
//        assert(this.items != null)
    }



    fun saveItems() {
        val gson = Gson()
        print("Saving new item to " + this.itemsFile.absolutePath)
        val writer = this.itemsFile.bufferedWriter()
        gson.toJson(this.items, writer)
    }

    fun getItems(): ArrayList<Item> {
        return this.items
    }

    fun addItem(item: Item) {
        assert(this.items != null)
        assert(item != null)
        this.items.add(item)
    }

    fun deleteItem(item: Item) {
        this.items.remove(item)
    }

    fun getItem(index: Int): Item {
        return this.items[index]
    }

    fun getIndex(item: Item): Int {
        for ((index, i) in this.items.withIndex()) { // Use withIndex for cleaner iteration
            if (item.id == i.id) { // Access id directly
                return index
            }
        }
        return -1
    }

    fun getSize(): Int {
        return this.items.size
    }


    fun filterItemsByStatus(status: String): ArrayList<Item> {
        val selectedItems = ArrayList<Item>()
        for (i in this.items) { // Simplified iteration
            if (i.status == status) { // Access status directly
                selectedItems.add(i)
            }
        }
        return selectedItems
    }

    fun getActiveBorrowers(): ArrayList<Contact> {
        val activeBorrowers = ArrayList<Contact>()
        for (i in this.items) {
            val borrower = i.borrower
            if (borrower != null) {
                activeBorrowers.add(borrower)
            }
        }
        return activeBorrowers
    } // end getActiveBorrowers

    fun toJson() {
        // convert the ItemList to a ItemListData object

        // use the ItemListData object's toJson

        // save to a .json file
    }

    fun fromJson() {
        // read a string from items.json file

        // convert the String object into an ItemListData object

        // create an empty ItemList object

        // turn each ItemData object into an Item object and store in the ItemList object
    }

} // end ItemList class


/*
 * Item List data class for serialization/deserialization purposes
 */
@Serializable
data class ItemListData(val items: ArrayList<ItemData>)

object JsonifyItemListData : Jsonify<ItemListData> {
    override val serializer : KSerializer<ItemListData> = ItemListData.serializer()

    override fun toJson(item : ItemListData) : String {
        return super.toJson(item)
    }
}
