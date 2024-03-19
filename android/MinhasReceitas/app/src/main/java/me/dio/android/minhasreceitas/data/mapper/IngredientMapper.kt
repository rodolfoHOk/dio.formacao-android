package me.dio.android.minhasreceitas.data.mapper

import me.dio.android.minhasreceitas.data.entity.IngredientEntity
import me.dio.android.minhasreceitas.domain.model.IngredientDomain

fun IngredientDomain.toEntity() = IngredientEntity(
    id = id,
    name = name,
    recipeId = recipeId
)

fun IngredientEntity.toDomain() = IngredientDomain(
    id = id,
    name = name,
    recipeId = recipeId
)
