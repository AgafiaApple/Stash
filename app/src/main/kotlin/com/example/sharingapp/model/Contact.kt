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
    val email : String) {

    @PrimaryKey(autoGenerate = true)
    val id : Long = 0
}