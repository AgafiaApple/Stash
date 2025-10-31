package com.example.sharingapp;

import java.util.UUID


class Contact {

    var username : String? = null
    var email : String? = null

    // TODO: when you get the database up, check to ensure that the id is not used by anyone else; alternatively, you could just use a automatically updating index as a key
    var id = UUID.randomUUID().toString()



    fun getId() : String {
        return this.id
    }

    fun setUsername(username : String) {
        if (this.username == null) {

            this.username = username
        }
    }
    fun getUsername() : String {
        return this.username!! // !! for null check
    }

    fun setEmail(email : String) {

        if (this.email == null) {
            if (!email.contains("@")) {
                throw IllegalArgumentException("Email must be a valid email")
            }
            this.email = email
        }
    } // end setEmail
    fun getEmail() : String {
        return this.email!!
    }



} // end Contact class

data class ContactData(val username : String, val email : String, val id : String)