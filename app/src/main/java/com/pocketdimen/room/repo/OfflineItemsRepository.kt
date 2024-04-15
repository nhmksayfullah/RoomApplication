package com.pocketdimen.room.repo

import com.pocketdimen.room.database.Item
import com.pocketdimen.room.database.ItemDao
import kotlinx.coroutines.flow.Flow

class OfflineItemsRepository(private val itemDao: ItemDao) : ItemsRepository {
    override fun getAllItemsStream(): Flow<List<Item>> = itemDao.getAllItems()

    override fun getItemStream(id: Int): Flow<Item?> = itemDao.getItem(id)

    override suspend fun writeItem(item: Item) = itemDao.write(item)

    override suspend fun deleteItem(item: Item) = itemDao.delete(item)

}