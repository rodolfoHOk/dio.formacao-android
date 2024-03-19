package me.dio.android.minhasreceitas.presentation.detail

import me.dio.android.minhasreceitas.presentation.model.ItemListModel

interface ItemListState {
    object Loading : ItemListState
    data class Success(
        val ingredients: List<ItemListModel>,
        val prepareMode: List<ItemListModel>
    ) : ItemListState
    data class Error(val message: String) : ItemListState
}
