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

class ContactList(var contacts: ArrayList<Contact>?, var FILENAME: String) {


    fun setContacts(contact_list : ArrayList<Contact>) {
        this.contacts = contact_list
    }

    fun getContacts() : ArrayList<Contact>?{
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
        if (this.contacts == null) {
            return 0
        } else {
            // !! is used as a non-null assertion and will
                // throw a NullPointerException if this.contacts is null
            return this.contacts!!.size
        }
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
        val contactsFile = getContactsFile(context)

        // 1. Open the file for reading
        //      ObjectInputStream reads the object from the file
        //      `use` ensures the file is closed immediately after use
        this.contacts = ObjectInputStream(FileInputStream(contactsFile)).use {
            // 2. Read the object from the file
            //      objectInputStream.readObject() reads the object that was written to the file
            //
            objectInputStream -> objectInputStream.readObject() as ArrayList<Contact>?
        }

        if (this.contacts == null) {
            println("Warning! `this.contacts` was loaded as `null`. This means either the user has no" +
                    " contacts yet or that an error occurred.")
        }
    } // end loadContacts()

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

    // saves the this.contacts in a file in ArrayList<Contact> format
    fun saveContacts(context : Context) {
        val contactsFile = getContactsFile(context)

        ObjectOutputStream(FileOutputStream(contactsFile)).use {
            objectOutputStream -> objectOutputStream.writeObject(this.contacts)
        }
    } // end saveContacts


    fun isUsernameAvailable(username : String) : Boolean {
        val iter = this.contacts?.listIterator()

        // enter while loop if iter is NOT null
        while(iter?.hasNext() ?: false) {
            val contact = iter.next()
            if (contact.getUsername().equals(username)) {
                return false
            }
        }

        return true
    } // end isUsernameAvailable

} // end ContactList class