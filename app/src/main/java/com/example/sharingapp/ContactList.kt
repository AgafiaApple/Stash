package com.example.sharingapp

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore

class ContactList(contacts : ArrayList<Contact>, FILENAME : String) {

    var contacts = contacts
    var FILENAME = FILENAME


    fun setContacts(contact_list : ArrayList<Contact>) {
        this.contacts = contact_list
    }

    fun getContacts() : ArrayList<Contact>{
        return this.contacts
    }

    fun getAllUsernames() : ArrayList<String> {

    }

    fun addContact(contact : Contact) {

    }

    fun deleteContact(contact : Contact) {

    }

    fun getContact(idx : Int) : Contact {

    }

    fun getSize() : Int {

    }

    fun getIndex(contact : Contact) {

    }

    fun hasContactByUsername(username : String) : Boolean {

    }

    fun getContactByUsername(username : String) : Contact {

    }

    fun loadContacts(context : Context) {

    }

    fun saveContacts(context : Context) {

    }

    fun isUsernameAvailable(username : String) : Boolean {

    }

} // end ContactList class