package com.example.sharingapp

import android.R
import android.content.Context
import java.io.File
import java.io.FileOutputStream
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.io.ObjectOutputStream
import java.security.InvalidParameterException

// TODO: you can get rid of some of the null checks by ensuring the default value for contacts is ArrayList<Contact>()

class ContactList(var contacts: ArrayList<Contact> = ArrayList<Contact>()) {

private val FILENAME = "contacts.json"
    @JvmName("setContactsFlow")
    fun setContacts(contactList : ArrayList<Contact>) {
        this.contacts = contactList
    }

    @JvmName("getContactsFlow")
    fun getContacts() : ArrayList<Contact>?{
        return this.contacts
    }

    fun getAllUsernames() : ArrayList<String> {
        val str_arr = ArrayList<String>()

        val iter = this.contacts.listIterator()

        // enter while loop if iter is NOT null
        while(iter.hasNext()) {
            str_arr.add(iter.next().getUsername())
        }

        return str_arr
    }

    fun addContact(contact : Contact) {

        this.contacts.add(contact)
    }

    fun deleteContact(contact : Contact) {
        // !! is used as a non-null assertion and will
        // throw a NullPointerException if this.contacts is null
        val success = this.contacts!!.remove(contact)

        if (!success) {
            throw InvalidParameterException("The given contact is not in the list of contacts.")
        }

    } // end deleteContact

    fun getContact(idx : Int) : Contact? {
        if (idx < this.contacts.size) {
               val contact = this.contacts[idx]
                return contact;
            } else {
                throw IndexOutOfBoundsException("The given index must be less than the size of the contacts list")
            } // end if-else

    } // end getContact()

    fun getSize() : Int {
        return this.contacts.size
    } // end getSize()

    // checks this.contacts
    fun getIndex(contact : Contact) : Int? {
        for (i in this.contacts.indices) {
                if (this.contacts[i].getUsername().equals(contact.getUsername())) {
                    return i
                }
            } // end for
        return null
    } // end getIndex()


    fun hasContactByUsername(username : String) : Boolean {
        val iter = this.contacts.listIterator()

        // enter while loop if iter is NOT null
        while(iter.hasNext()) {
            val contact = iter.next()
            if (contact.getUsername().equals(username)) {
                return true
            }
        }

        return false
    }

    // reads contacts' usernames from this.contacts
    fun getContactByUsername(username : String) : Contact? {
        val iter = this.contacts.listIterator()

        // enter while loop if iter is NOT null
        while(iter.hasNext()) {
            val contact = iter.next()
            if (contact.getUsername().equals(username)) {
                return contact
            }
        } // end while

        return null

    }// end getContactByUsername

    fun loadContacts(context : Context) {
        val gson = Gson()
        val reader = getContactsFile(context).bufferedReader()
        val listType = object : TypeToken<ArrayList<Contact>>() {}.type
        this.contacts = gson.fromJson(reader, listType)
    } // end loadContacts

    private fun getContactsFile(context : Context) : File {
        val contactsFile = File(context.filesDir, FILENAME)

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
        val gson = Gson()
        val writer = getContactsFile(context).bufferedWriter()
        gson.toJson(this.contacts, writer)

    } // end saveContacts


    fun isUsernameAvailable(username : String) : Boolean {
        val iter = this.contacts.listIterator()

        // enter while loop if iter is NOT null
        while(iter.hasNext()) {
            val contact = iter.next()
            if (contact.getUsername().equals(username)) {
                return false
            }
        }

        return true
    } // end isUsernameAvailable

} // end ContactList class