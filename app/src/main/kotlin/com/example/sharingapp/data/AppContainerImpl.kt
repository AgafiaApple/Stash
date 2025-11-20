package com.example.sharingapp.data

import android.content.Context
import com.example.sharingapp.data.contacts.ContactsRepository
import com.example.sharingapp.data.contacts.impl.FakeContactsRepository
import com.example.sharingapp.data.items.ItemsRepository
import com.example.sharingapp.data.items.impl.FakeItemsRepository

/**
 * dependency injection container at the app level
 */
interface AppContainer {

    val contactsRepository : ContactsRepository
    val itemsRepository : ItemsRepository
}

/**
 * Implementation for the dependency injection container
 *
 * Variables are initialized lazily and the same instance is shared across the whole app
 */

class AppContainerImpl(private val applicationContext: Context) : AppContainer {
    override val contactsRepository: ContactsRepository by lazy {
        FakeContactsRepository()
    }

    override val itemsRepository: ItemsRepository by lazy {
        FakeItemsRepository()
    }
}