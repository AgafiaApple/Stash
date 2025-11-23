package com.example.sharingapp.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.sharingapp.data.contacts.ContactsRepository
import com.example.sharingapp.model.Contact
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ContactUiState(
    val contacts:  List<Contact> = emptyList(),
    val isLoading: Boolean = false
)

class ContactsViewModel(private val repository : ContactsRepository) : ViewModel(){
    // private mutable state flow field
    val _uiState = MutableStateFlow(ContactUiState(isLoading = true))
    // public immutable state flow field
    val uiState = _uiState.asStateFlow()

    init {
        // call within a coroutine so we don't block the current thread
        viewModelScope.launch {
            // collect the flow -- this block runs forever, always listening for changes
            // The collect block immediately pushes to the UI
            val contacts = repository.getContacts().collect {updatedContactList ->
                // this runs whenever _contactsFlow.update is called in the repository
                val contactList = updatedContactList

                _uiState.update { it.copy(
                    contacts = contactList,
                    isLoading = false
                )}
            }


        }
    }

    fun deleteContact(id : Long) {
        viewModelScope.launch {
            // Because we are getting a Flow from the repository, we can just call delete
            repository.deleteContact(id)
        }

    }

    // implement factory creator object
    companion object {
        fun provideFactory(contactsRepository: ContactsRepository): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ContactsViewModel(contactsRepository) as T
                }
            }
    }
}