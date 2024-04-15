package com.pocketdimen.room

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pocketdimen.room.appconfig.InventoryApplication
import com.pocketdimen.room.screen.InventoryViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            InventoryViewModel(inventoryApplication().container.itemsRepository)
        }

    }
}

fun CreationExtras.inventoryApplication(): InventoryApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as InventoryApplication)