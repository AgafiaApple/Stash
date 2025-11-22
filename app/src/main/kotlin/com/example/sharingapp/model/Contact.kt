package com.example.sharingapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


/**
 * A Dao-compatible class for storing data about a user's contacts
 */
@Entity
@Serializable
data class Contact(val username : String,
    val email : String,
    val idx : Long? = null) {

    @PrimaryKey(autoGenerate = true)
    // idx will only be used for the fake contact repository since FakeItemRepository does not use a Room database
    val id : Long = idx ?: 0
}