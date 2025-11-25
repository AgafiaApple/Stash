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
            Contact("Dan", "DanTheDog", "dandog@gmail.com",
                description = "Just a blue-collared dog. If you need to borrow a grill, just bark.",
                idx = getThenIncrementIdx()),
            Contact("Percepolis", "PercyTheParrot", "percysquawks@proton.me",
                description = "Enjoys collecting feathers from other birds and has lots of quills to share.",
                idx = getThenIncrementIdx()),
            Contact("Jenny Judelson", "JustJenny", "jenny_8675309@gmail.com",
                description = "I don't have much of a collection yet, but I am eager to start!",
                idx = getThenIncrementIdx())
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
        return true
    }

    companion object {
        var nextIdx : Long = 0
        fun getThenIncrementIdx() : Long {
            val idx = nextIdx
            nextIdx = nextIdx + 1
            return idx
        }
    }
}