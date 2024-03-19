package me.dio.android.minhasreceitas.data.mapper

import me.dio.android.minhasreceitas.data.entity.PrepareModeEntity
import me.dio.android.minhasreceitas.domain.model.PrepareModeDomain

fun PrepareModeDomain.toEntity() = PrepareModeEntity(
    id = id,
    description = description,
    recipeId = recipeId
)

fun PrepareModeEntity.toDomain() = PrepareModeDomain(
    id = id,
    description = description,
    recipeId = recipeId
)
