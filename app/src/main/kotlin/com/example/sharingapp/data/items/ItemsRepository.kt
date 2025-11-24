package com.example.sharingapp.data.items

import com.example.sharingapp.model.Dimensions
import com.example.sharingapp.model.Item
import com.example.sharingapp.model.Status
import kotlinx.coroutines.flow.Flow

interface ItemsRepository {
     suspend fun getItems() : Flow<List<Item>>
     suspend fun addItem(item : Item) : Boolean
     suspend fun deleteItem(itemId : Long) : Boolean
     suspend fun updateItemStatus(itemId : Long, newStatus : Status)
     suspend fun updateItemName(itemId : Long, newName : String)
     suspend fun updateItemMaker(itemId : Long, newMaker : String)
     suspend fun updateItemDescription(itemId : Long, newDescription : String)
     suspend fun updateItemDimensions(itemId : Long, newDims : Dimensions)

}