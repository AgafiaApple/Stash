package com.example.sharingapp

import kotlinx.serialization.Serializable

@Serializable
data class Contact(val username : String,
    val email : String) {

    // data class functionality
    val id : Int get() = hashCode()
}