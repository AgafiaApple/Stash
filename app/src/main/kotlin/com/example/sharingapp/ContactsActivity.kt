package com.example.sharingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import com.example.sharingapp.ui.theme.AppTheme
import java.io.File
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

class ContactsActivity : ComponentActivity(){
    val FILENAME = "contacts.json"
    val contactsPath = filesDir.absolutePath.plus(FILENAME)
    val contactsFile = File(contactsPath)

    // TODO: next two lines are just fake test data that I will add to a test case soon
    val testContact = Contact("Agafia", "appleb@gmail.com")
    val testList = ContactListConstructor()


    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // TODO: remove next line b/c it needs to be in a test case fn
        testList.addContact(testContact)

        // make sure the file storing the contacts can be accessed and used
        prepareFile()

        // retrieve list, or get a new empty object to store contact info
        val contactsConstruct = getList()

        // populate the list view
        setContent {
            AppTheme {

                // populate the lazy list of contacts
                // TODO: replace with contactsConstrct b/c testList needs to be in a test case fn
                LazyContactsColumn(testList)
            } // end AppTheme
        } // end setContent
    } // end onCreate


    @Composable
    fun LazyContactsColumn(contactsConstruct : ContactListConstructor) {
        LazyColumn {
            items(contactsConstruct.contacts,
                key = {it}
            ) { contact ->
                Row() {
                    Text(contact.username)
                } // end Row
            } // end items param
        } // end LazyColumn
    }


    /**
     * prepareFile() ensures that the file
     * is created if it does not already exist
     * in internal memory
     */
    fun prepareFile() {
        // make sure the file is created if it does not already exist
        if (!this.contactsFile.isFile) {
            contactsFile.createNewFile()
        }

    } // end prepareFile() fn

    fun getList() : ContactListConstructor {
        // the mutable object used to edit, delete, and add the data
        // which will be saved to internal storage when the app stops running
        lateinit var contactsConstruct : ContactListConstructor

        // get the json string that represents the ContactList object
        val jsonStr = JsonFileOps().fromJsonFile(contactsPath)

        if (jsonStr != null && !jsonStr.isEmpty()) {
            // retrieve the contact list object from the json string representation
            val contactsListTemp = JsonifyContactList().fromJson(jsonStr)
            // convert it into a mutable format (ContactListConstructor)
            contactsConstruct = ContactListConstructor()
            // populate the contactsConstructor with the data retrieved from the json file
            for (contact in contactsListTemp.contacts) {
                contactsConstruct.addContact(contact)
            }
        } // end if
        else {
            // create a new object to hold the contacts
            contactsConstruct = ContactListConstructor()
        } // end else

        return contactsConstruct
    }
}