package com.pocketdimen.room.repo

import com.pocketdimen.room.database.Item
import kotlinx.coroutines.flow.Flow

interface ItemsRepository {
    fun getAllItemsStream(): Flow<List<Item>>
    fun getItemStream(id: Int): Flow<Item?>
    suspend fun writeItem(item: Item)
    suspend fun deleteItem(item: Item)
}
