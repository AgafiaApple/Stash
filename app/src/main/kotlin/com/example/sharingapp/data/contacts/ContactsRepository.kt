package com.example.sharingapp.data.contacts

import com.example.sharingapp.model.Contact
import kotlinx.coroutines.flow.Flow

interface ContactsRepository {

    // use Result to handle data success/failure gracefully
    // returns List<Contact> if successful, otherwise Exception
    suspend fun getContacts(): Flow<List<Contact>>

    suspend fun addContact(contact : Contact) : Boolean

    suspend fun deleteContact(id : Long) : Boolean

}