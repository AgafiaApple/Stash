package com.example.sharingapp.data.items.impl

import android.util.Log
import com.example.sharingapp.data.items.ItemsRepository
import com.example.sharingapp.model.Dimensions
import com.example.sharingapp.model.Item
import com.example.sharingapp.model.Status

class FakeItemsRepository : ItemsRepository {
    private val items by lazy {
        mutableListOf(
            Item("Lawn Mower", "John Deere", "A well-loved grass-eating machine (rust warning)",
                Dimensions(5, 2, 2)),
            Item("A Big Sword", "Zabuza inc.", "It's really big.", Dimensions(7, 0, 0)),
            Item("Flowbee", "Rick E. Hunts", "An innovative at-home haircutting system for the brave of heart.",
                Dimensions(15, 8, 4)),
            Item("French Fry Holder", "Yours Truly", "Down with the cups, in with the fries.",
                Dimensions(1, 1, 1))
        )
    }

    override suspend fun getItems(): List<Item> {
        return this.items
    }

    override suspend fun addItem(item: Item): Boolean {
        this.items.add(item)
        return true

    }

    override suspend fun deleteItem(itemId: Long): Boolean {
        for (item in this.items)  {
            if (item.id == itemId) {
                this.items.remove(item)
                return true
            }
        }
        return false
    }

    override suspend fun updateItemStatus(
        itemId: Long,
        newStatus: Status
    ) {
        for (item in this.items)  {
            if (item.id == itemId) {
                item.status = newStatus
                break
            }
        }
        Log.d("invalid item", "invalid item given, item status NOT updated")
    }

    override suspend fun updateItemName(itemId: Long, newName: String) {
        for (item in this.items)  {
            if (item.id == itemId) {
                item.title = newName
                break
            }
        }
        Log.d("invalid item", "invalid item given, item name NOT updated")
    }

    override suspend fun updateItemMaker(itemId: Long, newMaker: String) {
        for (item in this.items)  {
            if (item.id == itemId) {
                item.maker = newMaker
                break
            }
        }
        Log.d("invalid item", "invalid item given, item maker NOT updated")

    }

    override suspend fun updateItemDescription(itemId: Long, newDescription: String) {
        for (item in this.items)  {
            if (item.id == itemId) {
                item.description = newDescription
                break
            }
        }
        Log.d("invalid item", "invalid item given, item description NOT updated")

    }

    override suspend fun updateItemDimensions(
        itemId: Long,
        newDims: Dimensions
    ) {
        for (item in this.items)  {
            if (item.id == itemId) {
                item.dims = newDims
                break
            }
        }
        Log.d("invalid item", "invalid item given, item dimensions NOT updated")

    }


}