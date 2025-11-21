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
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// HomeUiState prototype
data class HomeUiState(
    val items : List<Item> = emptyList(),
    val isLoading: Boolean = false
)

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
    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState = _uiState.asStateFlow() // Note to self: why do we do this?

    init {
        viewModelScope.launch {
            // direct assignment for now
            val items = repository.getItems()
            _uiState.update { it.copy(items = items, isLoading = false)}
        }
    }
    // implement factory
    companion object {
        fun provideFactory(itemsRepository: ItemsRepository): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ItemsViewModel(itemsRepository) as T
                }
            }
    }



    /**
     * Uses a background thread to retrieve the items from the ItemsRepository.
     * This is called when initialized the ItemsViewModel
     */
//    fun loadItems() {
//        viewModelScope.launch {
//
//        }
//    }

    /**
     *
     */
    fun onUpdateItemStatus(itemId : Long) {

    }

    fun onDeleteItem(itemId : Long) {

    }


} // end class ItemsViewModel