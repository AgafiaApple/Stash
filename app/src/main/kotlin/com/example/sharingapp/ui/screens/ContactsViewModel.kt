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
        viewModelScope.launch {
            // direct assignment for now
            val contacts = repository.getContacts()
            // TODO: change to getOrNull later b/c it's safer
            _uiState.update { it.copy(contacts = contacts.getOrThrow(), isLoading = false)}
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