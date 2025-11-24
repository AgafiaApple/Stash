package com.example.sharingapp.data.items

// room annotation processor
// room writes code in the background
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sharingapp.model.Dimensions
import com.example.sharingapp.model.Item
import com.example.sharingapp.model.Status
import kotlinx.coroutines.flow.Flow

const val ITEM_TABLE_NAME = "Item"

/**
 * ItemDao is utilized by a concrete Item Repository class
 * for the purpose of efficient data access
 */
@Dao // data access object
// we can do this because our Item instances each have a primary key :D
interface ItemDao {


    // these functions are not "abstract" in the typical sense
    // `suspend` tells the compiler:
    //      1. potentially a long-running operation
    //      2. can only be called from a coroutine
    //      3. can pause and resume so that it doesn't block other threads

    // we can do this b/c the `Item` class uses the @Entity notation
    @Query("SELECT * FROM " + ITEM_TABLE_NAME)
    suspend fun getItems() : Flow<List<Item>>

    @Query("DELETE FROM " + ITEM_TABLE_NAME + " WHERE id = :itemId")
    suspend fun deleteItem(itemId : Long) : Long

    @Query("UPDATE " + ITEM_TABLE_NAME + " SET title = :newName WHERE id = :itemId" )
    suspend fun updateName(itemId : Long, newName : String)

    @Query("UPDATE " + ITEM_TABLE_NAME + " SET description = :newDescription WHERE id = :itemId")
    suspend fun updateDescription(itemId : Long, newDescription : String)

    @Query("UPDATE " + ITEM_TABLE_NAME + " SET dims = :newDims WHERE id = :itemId ")
    suspend fun updateDimensions(itemId : Long, newDims : Dimensions)

    @Query("UPDATE " + ITEM_TABLE_NAME + " SET maker = :newMaker WHERE id = :itemId ")
    suspend fun updateMaker(itemId : Long, newMaker : String)

    @Query("UPDATE " + ITEM_TABLE_NAME + " SET status = :newStatus WHERE id :itemId")
    suspend fun updateStatus(itemId : Long, newStatus : Status)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addItem(item : Item) : Long // if the rowId (the returned Long) > -1, the insert is successful

} // end ItemDao interface

