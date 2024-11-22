package com.library.paginationusingroom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow

class ItemViewModel(private val repository: ItemRepository) : ViewModel() {
    val items: Flow<PagingData<ItemEntity>> = repository.getPagedItems().cachedIn(viewModelScope)
}
