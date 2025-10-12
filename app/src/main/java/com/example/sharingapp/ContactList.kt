package com.example.sharingapp

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import java.io.File
import java.io.Serializable
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

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

    // checks this.contacts
    fun getIndex(contact : Contact) {

    }


    fun hasContactByUsername(username : String) : Boolean {

    }

    // reads contacts' usernames from this.contacts
    fun getContactByUsername(username : String) : Contact {

    }

    // loads the contacts from a file into this.contacts
    fun loadContacts(context : Context) {




    }

    private fun getContactsFile(context : Context) : File {
        val contactsFile = File(context.filesDir, "contacts")

        if (!contactsFile.exists()) {
            try {
                if (contactsFile.createNewFile()) {
                    println("File created: ${contactsFile.absolutePath}")
                } else {
                    println("File already exists even though `!contactsFile.exists()` returned true - check for errors")
                }
            } catch (e: IOException) {
                e.printStackTrace()
                println("Error creating file ${e.message}")
            } // end try-catch

        } else {
            println("File already exists: ${contactsFile.absolutePath}")
        } // end if-else

        return contactsFile

    } // end getContactsFile

    // saves the this.contacts in a file in ArrayList format
    fun saveContacts(context : Context) {


    }


    fun isUsernameAvailable(username : String) : Boolean {

    }

} // end ContactList class