package com.example.sharingapp

import android.util.Log
import kotlinx.serialization.Serializable

@Serializable
data class ContactList(val contacts : ArrayList<Contact>)


// I think this is good practice...
// promoting functional-style, more bug-free code because
// we isolate side effects

class ContactListConstructor {
    var contacts = ArrayList<Contact>()

    fun addContact(contact : Contact) : Boolean {
        return contacts.add(contact)
    }

    fun deleteContact(contact : Contact) : Boolean {
        return contacts.remove(contact)
    }

    fun getContactFromIdx(idx : Int) : Contact? {
        if (idx < contacts.size) {
            return contacts[idx]
        }

        // log the event if index is out of bounds
        Log.d(null, "Index out of bounds; returning null")

        return null
    }

    fun toContactList() : ContactList {
        return ContactList(contacts=contacts)
    }
}

class JsonifyContactList : Jsonify<ContactList> {
    // we can use .serializer() b/c ContactList is
    // a @Serializable data class
    override val serializer = ContactList.serializer()
}