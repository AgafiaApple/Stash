package com.example.sharingapp

import android.os.Bundle
import android.util.Log
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
import com.example.sharingapp.model.Contact
import com.example.sharingapp.model.Item
import com.example.sharingapp.model.Status
import kotlinx.serialization.Serializable

class ContactsActivity : ComponentActivity(){
    val FILENAME = "contacts.json"
    val contactsPath = filesDir.absolutePath.plus(FILENAME)
    val contactsFile = File(contactsPath)

    // TODO: next two lines are just fake test data that I will add to a test case soon
    val testContact = Contact("Agafia", "agafiaapple", "appleb@gmail.com", description = "Beep-bobbity-boop")
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

@Serializable
data class ItemList(val items : ArrayList<Item>)

// I think this is good practice...
// promoting functional-style, more bug-free code because
// we isolate side effects
class ItemListConstructor {
    var items = ArrayList<Item>()

    fun addItem(item : Item) : Boolean {
        return items.add(item)
    }

    fun deleteItem(item : Item) : Boolean {
        return items.remove(item)
    }

    fun getItemFromIdx(idx : Int) : Item? {
        if (idx < items.size) {
            return items[idx]
        }

        // log the event if index is out of bounds
        Log.d(null, "Index out of bounds; returning null")

        return null
    }

    fun toItemList() : ItemList {
        return ItemList(items=items)
    }

    fun filterList(items_ls : ItemList, filter : Status) : ItemList {
        var filtered_arr = ArrayList<Item>()

        // add items that match the filter param
        // to the filtered_arr
        for (item in items_ls.items) {
            if (item.status == filter) {
                filtered_arr.add(item)
            }
        } // end for


        return ItemList(filtered_arr)

    } // end filterList()

} // end ItemListConstructor

class JsonifyItemList : Jsonify<ItemList> {
    // we can use .serializer() b/c ItemList is
    // a @Serializable data class
    override val serializer = ItemList.serializer()
}