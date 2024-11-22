package com.library.paginationusingroom

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class ItemRepository(
    private val database: AppDatabase,
//    private val apiService: ApiService
) {
    @OptIn(ExperimentalPagingApi::class)
    fun getPagedItems(): Flow<PagingData<ItemEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            remoteMediator = ItemRemoteMediator(database),
            pagingSourceFactory = { database.itemDao().getPagingSource() }
        ).flow
    }
}
