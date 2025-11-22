package com.example.sharingapp.data.contacts.impl

import com.example.sharingapp.data.contacts.ContactsRepository
import com.example.sharingapp.model.Contact
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
class FakeContactsRepository : ContactsRepository {

    private val contacts by lazy {
        mutableListOf(
            Contact("DanTheDog", "dandog@gmail.com", 0),
            Contact("PercyTheParrot", "percysquawks@proton.me", 1),
            Contact("JustJenny", "jenny_8675309@gmail.com", 2)

        )
    }


    override suspend fun getContacts(): Result<MutableList<Contact>> {
        return Result.success(contacts)
    }

    override suspend fun addContact(contact: Contact): Boolean {
        this.contacts.add(contact)
        return true
    }

    override suspend fun deleteContact(id: Long): Boolean {
        for (contact in contacts) {
            if (contact.id == id) {
                contacts.remove(contact)
                return true
            }
        }
        // if the contact with that id was not found
        return false
    }
}