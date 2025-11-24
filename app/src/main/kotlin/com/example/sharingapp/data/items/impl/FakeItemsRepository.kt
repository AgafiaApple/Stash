package com.example.sharingapp.data.items.impl

import android.util.Log
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import com.example.sharingapp.data.items.ItemsRepository
import com.example.sharingapp.model.Dimensions
import com.example.sharingapp.model.Item
import com.example.sharingapp.model.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.getValue

class FakeItemsRepository : ItemsRepository {
    private val _itemsFlow by lazy {
        MutableStateFlow(
            listOf(
                Item("Lawn Mower", "John Deere", "A well-loved grass-eating machine (rust warning)",
                    Dimensions(5, 2, 2)),
                Item("A Big Sword", "Zabuza inc.", "It's really big.", Dimensions(7, 0, 0)),
                Item("Flowbee", "Rick E. Hunts", "An innovative at-home haircutting system for the brave of heart.",
                    Dimensions(15, 8, 4)),
                Item("French Fry Holder", "Yours Truly", "Down with the H20, in with the NaCl.",
                    Dimensions(1, 1, 1))
            )
        )
    }

    override suspend fun getItems(): Flow<List<Item>> {
        return this._itemsFlow.asStateFlow()
    }

    override suspend fun addItem(item: Item): Boolean {
        this._itemsFlow.update { currentList ->
            currentList + item
        }
        return true

    }

    override suspend fun deleteItem(itemId: Long): Boolean {
        this._itemsFlow.update { currentList ->
            // TODO: I think it is, but make sure the item is permanently deleted from repo 
            currentList.filter {it.id != itemId}

        }
        return true

    }

    override suspend fun updateItemStatus(
        itemId: Long,
        newStatus: Status
    ) {
        this._itemsFlow.update { currentList ->
            val exists = currentList.any {it.id == itemId}
            
            // if the item is not in the list, return the unchanged list 
            if (!exists) {
                Log.d("invalid item", "invalid item given, item status NOT updated")
                return@update currentList
            }
            
            // return a new list with the changed item if the item is in the list
            currentList.map { item -> 
                if(item.id == itemId) {
                    item.copy(status = newStatus) // .copy can only be used with constructor parameters
                }
                else {
                    item 
                }
                
            }
        }
        
    }

    override suspend fun updateItemName(itemId: Long, newName: String) {
        this._itemsFlow.update { currentList ->
            val exists = currentList.any {it.id == itemId}

            if (!exists) {
                Log.d("invalid item", "invalid item given, item name NOT updated")
                return@update currentList
            }

            currentList.map {item ->
                if(item.id == itemId) {
                    // if the current item's ID matches, change its name
                    item.copy(title = newName)
                }
                else {
                    // otherwise don't change item
                    item
                }
            }

        }
    }

    override suspend fun updateItemMaker(itemId: Long, newMaker: String) {
        this._itemsFlow.update {currentList ->
            val exists = currentList.any {it.id == itemId}

            if (!exists) {
                Log.d("invalid item", "invalid item given, item maker NOT updated")
                return@update currentList
            }

            currentList.map {item ->
                if (item.id == itemId) {
                    item.copy(maker = newMaker)
                }
                else {
                    item
                }
            }
        }

    }

    override suspend fun updateItemDescription(itemId: Long, newDescription: String) {
        this._itemsFlow.update { currentList ->
            val exists = currentList.any {it.id == itemId}
            if (!exists) {
                Log.d("invalid item", "invalid item given, item description NOT updated")
                return@update currentList
            }

            currentList.map { item ->
                if (item.id == itemId) {
                    item.copy(description = newDescription)
                }
                else {
                    item
                }
            }
        }

    }

    override suspend fun updateItemDimensions(
        itemId: Long,
        newDims: Dimensions
    ) {
        this._itemsFlow.update { currentList ->
            // check if the item even exists in the repo
            val exists = currentList.any {it.id == itemId}
            // if it doesn't exist, create log msg and return unchanged list
            if (!exists) {
                Log.d("invalid item", "invalid item given, item description NOT updated")
                return@update currentList
            }

            // if it does exist, map the matching item to the item id and change that item within currentList
            currentList.map {item ->
                if (item.id == itemId) {
                    item.copy(dims = newDims)
                }
                else {
                    item
                }
            }

        }

    }


}