package com.example.sharingapp.data.contacts.impl

import com.example.sharingapp.data.contacts.ContactsRepository
import com.example.sharingapp.model.Contact
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

@OptIn(ExperimentalCoroutinesApi::class)
class FakeContactsRepository : ContactsRepository {

    // Use a MutableStateFlow to hold the list of data
    // this acts like an updating database (If we were
    // to use Dao here, it could do it more automatically)
    private val _contactsFlow by lazy {
        MutableStateFlow(listOf(
            Contact("DanTheDog", "dandog@gmail.com", 0),
            Contact("PercyTheParrot", "percysquawks@proton.me", 1),
            Contact("JustJenny", "jenny_8675309@gmail.com", 2)
        )

        ) // end MutableStateFlow val
    }


    // return a Flow so it can automatically update for the ViewModel
    override suspend fun getContacts(): Flow<List<Contact>> {
        return _contactsFlow.asStateFlow()

    }

    override suspend fun addContact(contact: Contact): Boolean {

        _contactsFlow.update {currentList ->
            currentList + contact
        }
        return true
    }

    override suspend fun deleteContact(id: Long): Boolean {
        _contactsFlow.update { currentList ->
            currentList.filter {it.id != id}
        }
        // if the contact with that id was not found
        return true
    }
}