package com.example.sharingapp;

import java.util.UUID

// var username = username and var email = email automatically occur with the below constructor
class Contact(var username: String, var email : String) {

    // TODO: when you get the database up, check to ensure that the id is not used by anyone else; alternatively, you could just use a automatically updating index as a key
    // TODO: I don't want to be able to set the id
    var id = UUID.randomUUID().toString() // getter and setter automatically made

} // end Contact class

data class ContactData(val username : String, val email : String, val id : String)