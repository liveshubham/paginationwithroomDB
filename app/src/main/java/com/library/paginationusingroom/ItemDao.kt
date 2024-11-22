package com.library.paginationusingroom

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItemDao {
    // Insert new items
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<ItemEntity>)

    // Clear all data
    @Query("DELETE FROM items")
    suspend fun clearAll()

    // PagingSource for fetching paginated data
    @Query("SELECT * FROM items ORDER BY id ASC")
    fun getPagingSource(): PagingSource<Int, ItemEntity>
}