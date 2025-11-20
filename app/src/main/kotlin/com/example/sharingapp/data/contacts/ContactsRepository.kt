package com.example.sharingapp.data.contacts

import com.example.sharingapp.model.Contact

interface ContactsRepository {

    // use Result to handle data success/failure gracefully
    // returns List<Contact> if successful, otherwise Exception
    suspend fun getContacts(): Result<List<Contact>>

    suspend fun addContact(contact : Contact) : Boolean

    suspend fun deleteContact(id : Long) : Boolean

}