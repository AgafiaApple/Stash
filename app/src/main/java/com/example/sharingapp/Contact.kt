package com.example.sharingapp;

import java.util.UUID


class Contact(username : String,
              email : String,
              id : String?) {

    final var username : String
    final var email : String
    var id = id ?: UUID.randomUUID().toString()


    init {
        // TODO: if both email and username are invalid, Error should account for that so caller knows what to show the user
        if (!email.contains("@")) {
            throw IllegalArgumentException("Email must be a valid email")
        }
        this.email = email

        // TODO: check for valid username
        this.username = username
    } // end init

// commenting out because it is unnecessary
//    fun setId() {
//        this.id = UUID.randomUUID().toString()
//    }

    fun getId() : String {
        return this.id
    }

// commenting out because it is unnecessary
//    fun updateId(id : String) {
//        this.id = id
//    }

    fun getUsername() : String {
        return this.username
    }

    fun getEmail() : String {
        return this.email
    }



} // end Contact class
