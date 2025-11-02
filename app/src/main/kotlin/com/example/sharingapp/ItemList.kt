package com.example.sharingapp

import android.content.Context
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


    // the method that will be called in the activities when loading the items
    fun initializeItemList(context: Context) {

        this.initializeItemsFile(context)

        // loads the previously saved list of items
        this.loadItems()
    }


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
        // if initialized but the file does not exist
        } else if (!this.itemsFile.exists()) {
            throw FileNotFoundException("this.itemsFile has been initialized but this.itemsFile's file path does not exist.")

        } else {
            println("this.itemsFile has already been initialized, and the file path is valid.")
        }
    }

    fun loadItems() {
        this.items = this.fromJson()
    }


    fun saveItems() {
        this.toJson()
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
        // to convert the ItemList to a ItemListData object, first make an ArrayList<ItemData> obj
        val tempDataList = ArrayList<ItemData>()

        this.items.forEach {
           val tempDataItem = ItemData(it.title,
               it.maker,
               it.description,
               it.dimensions,
               it.image_base64)

            tempDataList.add(tempDataItem)
        }

        // creating the ItemListData obj so we can write the data to the .json file
        val dataList = ItemListData(tempDataList)

        // use the ItemListData object's toJson
        val jsonStr = JsonifyItemListData.toJson(dataList)

        // save to a .json file
        this.itemsFile.bufferedWriter().use {out -> // .use ensures the file closes regardless of success
            out.write(jsonStr)
        }
    }


    fun fromJson() : ArrayList<Item> {

        // read a string from items.json file
        val jsonStr = itemsFile.bufferedReader().use {it.readText()}

        // convert the String object into an ItemListData object
        val dataList = JsonifyItemListData.fromJson(jsonStr)

        // create an empty ItemList object
        val itemList = ItemList()

        // turn each ItemData object into an Item object and store in the ItemList object
        dataList.items.forEach {
            val tempItem = Item(it.title,
                it.maker,
                it.description,
                it.dims)

            itemList.addItem(tempItem)

        }

        return itemList.items
    } // end fromJson

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
