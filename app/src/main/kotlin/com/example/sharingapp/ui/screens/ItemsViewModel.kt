package com.example.sharingapp.ui.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sharingapp.data.items.ItemsRepository
import com.example.sharingapp.model.Item
import kotlinx.coroutines.launch
// next two imports needed for `by`
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * The ItemsViewModel provides the data and operations that the corresponding View
 * will use to make an interactive user experience for the Items screen
 */
class ItemsViewModel(private val repository: ItemsRepository) : ViewModel() {
    // 1. a state holder that the ui can observe for changes
    //    initialized with an empty list so it's never null

    // `by` is a type of "delegated property" feature
    // under the hood, `by` does:
    //  private val _uiState: MutableState<List<Item>> = mutableStateOf(emptyList())
    var uiState by mutableStateOf<List<Item>>(emptyList())
        private set

    init {
        loadItems()
    }

    /**
     * Uses a background thread to retrieve the items from the ItemsRepository.
     * This is called when initialized the ItemsViewModel
     */
    fun loadItems() {
        viewModelScope.launch {
            // the operation "under the hood is _uiState.value = repository.getItems()
            uiState = repository.getItems()
        }
    }

    /**
     *
     */
    fun onUpdateItemStatus(itemId : Long) {

    }

    fun onDeleteItem(itemId : Long) {

    }


} // end class ItemsViewModel