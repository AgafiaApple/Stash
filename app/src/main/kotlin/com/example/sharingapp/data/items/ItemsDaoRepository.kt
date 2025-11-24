package com.example.sharingapp.data.items

import com.example.sharingapp.model.Dimensions
import com.example.sharingapp.model.Item
import com.example.sharingapp.model.Status
import kotlinx.coroutines.flow.Flow

/**
 * Implements the ItemsRepository interface
 * to access and update data via the
 * data-access-object `ItemDao`
 *
 * Is utilized by the ItemsViewModel of the application
 */
class ItemsDaoRepository(private val itemDao : ItemDao) : ItemsRepository {
    override suspend fun getItems(): Flow<List<Item>> {
        return itemDao.getItems()
    }

    override suspend fun addItem(item: Item): Boolean {
        val bool = itemDao.addItem(item) > -1

        // true/successful if the returned Id that was inserted is greater than -1
        return bool
    }

    override suspend fun deleteItem(itemId: Long): Boolean {
        val bool = itemDao.deleteItem(itemId) > -1

        return bool
    }

    override suspend fun updateItemStatus(
        itemId: Long,
        newStatus: Status
    ) {
        itemDao.updateStatus(itemId, newStatus)
    }

    override suspend fun updateItemName(itemId: Long, newName: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updateItemMaker(itemId: Long, newMaker: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updateItemDescription(itemId: Long, newDescription: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updateItemDimensions(
        itemId: Long,
        newDims: Dimensions
    ) {
        TODO("Not yet implemented")
    }

}