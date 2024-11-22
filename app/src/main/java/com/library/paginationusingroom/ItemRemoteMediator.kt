package com.library.paginationusingroom

import androidx.paging.*
import androidx.room.withTransaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagingApi::class)
class ItemRemoteMediator(
    private val database: AppDatabase,
) : RemoteMediator<Int, ItemEntity>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, ItemEntity>): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> 1 // Initial load
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull() ?: return MediatorResult.Success(endOfPaginationReached = true)
                lastItem.page + 1 // Increment page number for API
            }
        }

        return try {
            // Fetch data from API
//            val response = apiService.getItems(page, state.config.pageSize) // API call
//            val items = response.map { ItemEntity(it.id, it.name, it.description, page) }

            // Save data into Room
            CoroutineScope(Dispatchers.IO).launch {
                if (loadType == LoadType.REFRESH) {
                    database.itemDao().clearAll() // Clear old data
                }
                database.itemDao().insertAll(items) // Save new data
            }

            MediatorResult.Success(endOfPaginationReached = items.isEmpty())
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}
