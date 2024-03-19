package me.dio.android.minhasreceitas.presentation.mapper

import me.dio.android.minhasreceitas.domain.model.IngredientDomain
import me.dio.android.minhasreceitas.domain.model.PrepareModeDomain
import me.dio.android.minhasreceitas.presentation.model.ItemListModel

fun IngredientDomain.toModel() = ItemListModel(
    id = id,
    name = name
)

fun List<IngredientDomain>.toModelIngredient() = map {
    it.toModel()
}

fun PrepareModeDomain.toModel() = ItemListModel(
    id = id,
    name = description
)

fun List<PrepareModeDomain>.toModelPrepareMode() = map {
    it.toModel()
}
