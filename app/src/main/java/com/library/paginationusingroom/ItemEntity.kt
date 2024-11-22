package com.library.paginationusingroom

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "items")
data class ItemEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val page: Int // Optional: Helps track the page in case of debugging
)