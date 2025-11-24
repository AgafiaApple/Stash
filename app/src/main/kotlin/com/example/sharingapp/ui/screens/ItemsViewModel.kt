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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toList
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
            val items = repository.getItems().collect {updatedItemsList ->
                // this runs whenever _itemsFlow.update is called in the repository
                val itemsList = updatedItemsList
                _uiState.update { it.copy(items = itemsList, isLoading = false)}
            }
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
    fun onUpdateItemTitle(itemId : Long, newTitle : String) {
        viewModelScope.launch {
            repository.updateItemName(itemId = itemId, newName = newTitle)
        }
    }

    fun onUpdateItemMaker(itemId : Long, newMaker : String) {
        viewModelScope.launch {
            repository.updateItemMaker(itemId = itemId, newMaker = newMaker)
        }
    }

     fun onUpdateItemDescription(itemId: Long, newDescription : String) {
         viewModelScope.launch {
             repository.updateItemDescription(itemId = itemId, newDescription = newDescription)
         }
    }

    fun onUpdateItemStatus(itemId : Long) {

    }

    fun onDeleteItem(itemId : Long) {
        viewModelScope.launch {
            repository.deleteItem(itemId)
        }
    }

    fun onAddItem(item : Item) {
        viewModelScope.launch {
            repository.addItem(item)
        }
    }


} // end class ItemsViewModel