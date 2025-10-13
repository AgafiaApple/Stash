package com.example.sharingapp

import android.content.Context
import java.io.File
import java.io.Serializable
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.security.InvalidParameterException

class ContactList(var contacts: ArrayList<Contact>?, var FILENAME: String) {


    fun setContacts(contact_list : ArrayList<Contact>) {
        this.contacts = contact_list
    }

    fun getContacts() : ArrayList<Contact>?{
        return this.contacts
    }

    fun getAllUsernames() : ArrayList<String> {
        val str_arr = ArrayList<String>()

        val iter = this.contacts?.listIterator()

        // enter while loop if iter is NOT null
        while(iter?.hasNext() ?: false) {
            str_arr.add(iter.next().getUsername())
        }

        return str_arr
    }

    fun addContact(contact : Contact) {
        if (this.contacts == null) {
            this.setContacts(ArrayList<Contact>())
        }

        this.contacts!!.add(contact)
    }

    fun deleteContact(contact : Contact) {
        if (this.contacts != null) {
            // !! is used as a non-null assertion and will
            // throw a NullPointerException if this.contacts is null
            val success = this.contacts!!.remove(contact)

            if (!success) {
                throw InvalidParameterException("The given contact is not in the list of contacts.")
            }
        } else {
            throw NullPointerException("The list of contacts is empty.")
        }

    } // end deleteContact

    fun getContact(idx : Int) : Contact? {
        if (this.contacts != null) {
            if (idx < this.contacts!!.size) {
               val contact = this.contacts!![idx]
            } else {
                throw IndexOutOfBoundsException("The given index must be less than the size of the contacts list")
            } // end if-else
        } else {
            throw NullPointerException("The list of contacts is empty.")
        } // end if-else

        // will never be reached but necessary for the function to be valid
        return null
    } // end getContact()

    fun getSize() : Int {
        if (this.contacts == null) {
            return 0
        } else {
            // !! is used as a non-null assertion and will
                // throw a NullPointerException if this.contacts is null
            return this.contacts!!.size
        }
    } // end getSize()

    // checks this.contacts
    fun getIndex(contact : Contact) : Int? {
        if (this.contacts != null) {
            // !! to assert that this.contacts cannot be null
            for (i in this.contacts!!.indices) {
                if (this.contacts!![i].getUsername().equals(contact.getUsername())) {
                    return i
                }
            } // end for
        } // end if

        return null
    } // end getIndex()


    fun hasContactByUsername(username : String) : Boolean {
        val iter = this.contacts?.listIterator()

        // enter while loop if iter is NOT null
        while(iter?.hasNext() ?: false) {
            val contact = iter.next()
            if (contact.getUsername().equals(username)) {
                return true
            }
        }

        return false
    }

    // reads contacts' usernames from this.contacts
    fun getContactByUsername(username : String) : Contact? {
        val iter = this.contacts?.listIterator()

        // enter while loop if iter is NOT null
        while(iter?.hasNext() ?: false) {
            val contact = iter.next()
            if (contact.getUsername().equals(username)) {
                return contact
            }
        } // end while

        return null

    }// end getContactByUsername

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
        // I should change "contacts.dat" to just a constant FILENAME variable
        val contactsFile = File(context.filesDir, "contacts.dat")

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