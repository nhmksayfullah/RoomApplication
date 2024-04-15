package com.pocketdimen.room.appconfig

import android.content.Context
import com.pocketdimen.room.database.InventoryDatabase
import com.pocketdimen.room.repo.ItemsRepository
import com.pocketdimen.room.repo.OfflineItemsRepository

interface AppContainer {
    val itemsRepository: ItemsRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineItemsRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [ItemsRepository]
     */
    override val itemsRepository: ItemsRepository by lazy {
        OfflineItemsRepository(InventoryDatabase.getDatabase(context).itemDao())
    }
}