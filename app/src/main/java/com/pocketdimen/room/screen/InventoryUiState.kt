package com.pocketdimen.room.screen

import com.pocketdimen.room.database.Item

data class InventoryUiState(
    val items: List<Item> = emptyList(),
    val currentItemId: Int = -1,
    val currentItemName: String = "",
    val currentItemPrice: String = "",
    val currentItemQuantity: String = ""
)
// items, currentItem