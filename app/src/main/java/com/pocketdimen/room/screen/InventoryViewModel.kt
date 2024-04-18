package com.pocketdimen.room.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pocketdimen.room.database.Item
import com.pocketdimen.room.repo.ItemsRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class InventoryViewModel(private val itemsRepository: ItemsRepository) : ViewModel() {

    var uiState = MutableStateFlow(InventoryUiState())
        private set

    var job: Job? = null

    init {
        getItemStream()
    }

    fun onDeleteClick(item: Item) {

    }

    fun deleteItem(item: Item) {
        viewModelScope.launch {
            itemsRepository.deleteItem(item)
        }
    }

    fun onItemNameChange(name: String) {
        uiState.update {
            it.copy(
                currentItemName = name
            )
        }
    }
    fun onItemPriceChange(price: String) {
        uiState.update {
            it.copy(
                currentItemPrice = price
            )
        }
    }
    fun onItemQuantityChange(quantity: String) {
        uiState.update {
            it.copy(
                currentItemQuantity = quantity
            )
        }
    }

    fun onItemIdChange(id: Int) {
        uiState.update {
            it.copy(
                currentItemId = id
            )
        }
    }

    fun onSaveClicked() {
        viewModelScope.launch {
            if (uiState.value.currentItemName.isNotBlank()) {
                if (uiState.value.currentItemId != -1) {
                    itemsRepository.writeItem(
                        Item(
                            id = uiState.value.currentItemId,
                            name = uiState.value.currentItemName,
                            price = uiState.value.currentItemPrice.toDoubleOrNull() ?: 0.0,
                            quantity = uiState.value.currentItemQuantity.toIntOrNull() ?: 0
                        )
                    )
                } else {
                    itemsRepository.writeItem(
                        Item(
                            name = uiState.value.currentItemName,
                            price = uiState.value.currentItemPrice.toDoubleOrNull() ?: 0.0,
                            quantity = uiState.value.currentItemQuantity.toIntOrNull() ?: 0
                        )
                    )
                }
            }
        }
    }

    private fun getItemStream() {
        job?.cancel()
        job = itemsRepository.getAllItemsStream().onEach {items ->
            uiState.update {
                it.copy(
                    items = items
                )
            }
        }.launchIn(viewModelScope)
    }

}

